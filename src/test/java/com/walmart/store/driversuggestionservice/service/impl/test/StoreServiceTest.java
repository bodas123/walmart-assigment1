package com.walmart.store.driversuggestionservice.service.impl.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.walmart.driver.entity.Store;
import com.walmart.driver.repository.StoreDetailRepository;
import com.walmart.driver.service.impl.StoreService;

@RunWith(MockitoJUnitRunner.class)
public class StoreServiceTest {


	@InjectMocks
	private StoreService storeServiceImpl;

    @Mock
    private StoreDetailRepository storeDetailRepository;
    @Mock
    private ModelMapper modelMapper;

    private Store storeRequest; 
    private Store store;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
		storeRequest = new Store("1", 1.2, 2.4);
		store = new Store("1", 1.2, 2.4);

    }
    
    
    @Test
    public void testSaveStoreInfo() {
        Mockito.when(storeDetailRepository.save(Mockito.any())).thenReturn(store);
    	Store storeDetailObj=storeServiceImpl.saveStoreInfo((storeRequest));
    	Assert.assertNotNull(storeDetailObj);
    }
    
}
