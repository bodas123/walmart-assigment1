package com.walmart.driver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.driver.entity.DriverLocation;
import com.walmart.driver.service.impl.DriverService;

@RestController
@RequestMapping("/drivers")
public class DriverController {

	@Autowired
	private DriverService driverService;

	@GetMapping("nearest")
	public List<DriverLocation> getDrivers(@RequestParam("StoreID") String storeId,
			@RequestParam("N") Integer numberOfDrivers) throws Exception {
		return driverService.getDrivers(storeId, numberOfDrivers);
	}

	@PostMapping("location")
	public void addDriverLocation(@RequestBody DriverLocation driverLocation) {
		driverService.addDriverLocation(driverLocation);
	}
}
