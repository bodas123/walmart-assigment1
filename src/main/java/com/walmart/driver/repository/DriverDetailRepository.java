package com.walmart.driver.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.walmart.driver.entity.DriverLocation;

@Repository
public interface DriverDetailRepository  extends JpaRepository<DriverLocation, String>{

	String distance_query = "SELECT * ,SQRT(POW(69.1 * (latitude - 27.876), 2) + POW(69.1 * (-128.33 - longitude) * COS(latitude / 57.3), 2)) "
								+ "AS distance FROM driver_location  ORDER BY distance";
	

	@Query(value=distance_query
			, nativeQuery = true)
	List<DriverLocation> getNearestStore(Double startlat, Double startlng, Pageable pageable);
	
}
