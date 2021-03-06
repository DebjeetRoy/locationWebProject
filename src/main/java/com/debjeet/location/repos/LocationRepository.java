package com.debjeet.location.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.debjeet.location.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
	
	@Query(value="select type, count(type) from Location group by type")
	public List<Object[]> findByTypeAndTypeCount();
}
