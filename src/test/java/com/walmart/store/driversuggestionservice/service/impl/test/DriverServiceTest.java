package com.walmart.store.driversuggestionservice.service.impl.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.driver.entity.DriverLocation;
import com.walmart.driver.entity.Store;
import com.walmart.driver.repository.DriverDetailRepository;
import com.walmart.driver.repository.StoreDetailRepository;
import com.walmart.driver.service.impl.DriverService;

@RunWith(MockitoJUnitRunner.class)
public class DriverServiceTest {

	@InjectMocks
	private  DriverService  driverServiceImpl;

    @Mock
    private DriverDetailRepository driverDetailRepository;
    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private StoreDetailRepository storeDetailRepository;
    @Mock
    private ObjectMapper mapper;

    private DriverLocation driverLocation;
    private List<DriverLocation> driverDetails;
    private Store storeDetail;
    
    

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        driverLocation=new DriverLocation("1", 1.0, 1.9);
        storeDetail=new Store("1", 1.0, 1.9);
        driverDetails=new ArrayList<DriverLocation> ();
        driverDetails.add(driverLocation);
    }
    

    @Test
    public void testSaveStoreInfo() {
    	driverServiceImpl.saveDriverLocation(driverLocation);
    }

    @Test
    public void testPublishToKafka() {
    	driverServiceImpl.addDriverLocation(driverLocation);
    }

    @Test
    public void testPublishToKafkaException() throws JsonProcessingException {
        Mockito.when(mapper.writeValueAsString(Mockito.any(DriverLocation.class))).thenThrow(JsonProcessingException.class);
    	driverServiceImpl.addDriverLocation(driverLocation);
    }
    
    @Test
    public void testGetNearestNDrivers() throws Exception {
        Mockito.when(storeDetailRepository.findById(Mockito.anyString())).thenReturn(Optional.of(storeDetail));
       Mockito.when(driverDetailRepository.getNearestStore(Mockito.any(),
    		   Mockito.any(), Mockito.any())).thenReturn(driverDetails);
        Assert.assertNotNull(driverServiceImpl.getDrivers("1", 1));
    }

}
