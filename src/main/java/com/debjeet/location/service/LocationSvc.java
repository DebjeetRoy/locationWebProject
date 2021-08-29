package com.debjeet.location.service;

import java.util.List;

import com.debjeet.location.entities.Location;

public interface LocationSvc {
	Location saveLocation(Location location);
	Location updateLocation(Location location);
	void deleteLocation(Location location);
	Location getLocationById(int id);
	List<Location> getAllLocations();
}
