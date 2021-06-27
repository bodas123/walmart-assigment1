package com.walmart.driver.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.driver.entity.DriverLocation;
import com.walmart.driver.entity.Store;
import com.walmart.driver.repository.DriverDetailRepository;
import com.walmart.driver.repository.StoreDetailRepository;

@Service
public class DriverService{
	private final Logger logger = LoggerFactory.getLogger(DriverService.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;


	@Value(value = "${kafka.topic}")
	private String topicName;

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private DriverDetailRepository driverDetailRepository ;
	
	@Autowired
	private StoreDetailRepository storeDetailRepository ;
	
	public void addDriverLocation(final DriverLocation driverLocation) {
	    try {
			kafkaTemplate.send(topicName, mapper.writeValueAsString(driverLocation));
		} catch (JsonProcessingException e) {
			logger.error("Exception while Converting Object to json");
		}
	}
	
	public void saveDriverLocation(final DriverLocation driverDetail) {
		driverDetailRepository.save(driverDetail);
	}

	public List<DriverLocation> getDrivers(String storeId, Integer numberOfDrivers) throws Exception {
		Store storeDetail=storeDetailRepository.findById(storeId).orElseThrow(()->new Exception("Store Not found"));
		return driverDetailRepository.getNearestStore(storeDetail.getLatitude(), storeDetail.getLongitude(),
				PageRequest.of(0, numberOfDrivers));
	}
	
}
