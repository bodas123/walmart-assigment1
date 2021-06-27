package com.walmart.driver.kafka;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.driver.entity.DriverLocation;
import com.walmart.driver.service.impl.DriverService;

@Component
public class DriverLocationConsumer {

	public static final String TOPIC = "#{@environment.getProperty('kafka.topic')}";

	public static final String GROUP = "#{@environment.getProperty('kafka.consumer.group')}";

	@Autowired
	private ObjectMapper mapper;
	
	private final Logger logger = LoggerFactory.getLogger(DriverLocationConsumer.class);

	@Autowired
	private DriverService driverService;

	@KafkaListener(topics = TOPIC, groupId = GROUP)
	public void listenDriverLocation(String message) {
		try {
			DriverLocation driverDetail = mapper.readValue(message, DriverLocation.class);
			driverService.saveDriverLocation(driverDetail);
		} catch (IOException ex) {
			logger.error("Error while converting location : ".concat(ex.getMessage()));
		}

	}

}
