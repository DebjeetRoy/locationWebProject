package com.debjeet.location.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.debjeet.location.entities.Location;
import com.debjeet.location.repos.LocationRepository;
import com.debjeet.location.service.LocationSvc;
import com.debjeet.location.util.EmailUtil;
import com.debjeet.location.util.ReportUtil;

@Controller
public class LocationController {
		
	@Autowired 
	private LocationSvc service;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	ReportUtil reportUtil;
	
	@Autowired
	ServletContext sc;
	
	@Autowired
	LocationRepository locationRepository;
	
	@RequestMapping("/showCreate") 
	public String showCreate() { 
		return "createLocation"; 
	}

	@RequestMapping("/saveloc") 
	public String savelocation(@ModelAttribute("location") Location location, ModelMap modelMap) { 
		Location loc = service.saveLocation(location); 
		String msg = "Location saved with id : "+loc.getId();
		modelMap.addAttribute("msg", msg);
		emailUtil.sendEmail("spring123abcd@gmail.com", "Location Saved", "Location Saved Successfully and about to return a message...");
		
		return "createLocation"; 
	}
	
	@RequestMapping("/displayLocations")
	public String displayLocations(ModelMap modelMap) {
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}
	
	@RequestMapping("/deleteLocation")
	public String deleteLocation(@RequestParam("id") int id, ModelMap modelMap) 
	{
		Location location = service.getLocationById(id);
		service.deleteLocation(location);
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		
		return "displayLocations";
	}
	
	@RequestMapping("/showUpdate")
	public String showupdate(@RequestParam("id") int id, ModelMap modelMap) {
		Location location = service.getLocationById(id);
		modelMap.addAttribute("location", location);
		return "updateLocation";
	}
	
	@RequestMapping("/updateloc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		service.updateLocation(location);
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations",locations);
		return "displayLocations";
	}
	
	@RequestMapping("/generateReport")
	public String generateReport() {
		String path = sc.getRealPath("/");
		List<Object[]> data = locationRepository.findByTypeAndTypeCount();
		reportUtil.generatePieChart(path, data);
		return "report";
	}
}
