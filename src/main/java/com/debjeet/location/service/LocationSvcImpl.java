package com.debjeet.location.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.debjeet.location.entities.Location;
import com.debjeet.location.repos.LocationRepository;

@Service
public class LocationSvcImpl implements LocationSvc {

	@Autowired 
	private LocationRepository repository;

	@Override 
	public Location saveLocation(Location location) {	
		return repository.save(location); 
	}

	@Override 
	public Location updateLocation(Location location) { 
		return repository.save(location);
	}

	@Override 
	public void deleteLocation(Location location) {
		repository.delete(location); 
	}

	@Override public Location getLocationById(int id) { 
		Optional<Location> getLocation = repository.findById(id);

		if(getLocation.isPresent()) { 
			return getLocation.get(); 
		} 

		return null; 
	}

	@Override 
	public List<Location> getAllLocations() { 
		List<Location> loc = new ArrayList<>(); 
		loc = repository.findAll(); 
		return loc; 
	}
}
