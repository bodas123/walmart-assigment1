package com.walmart.store.driversuggestionservice.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.driver.controller.StoreController;
import com.walmart.driver.entity.Store;
import com.walmart.driver.service.impl.DriverService;
import com.walmart.driver.service.impl.StoreService;

@RunWith(MockitoJUnitRunner.class)
public class StoreControllerTest {

	private MockMvc mockMvc;
	
	@InjectMocks
	private StoreController storeController;
	
	@Mock
	private StoreService storeService;
	
	private Store store;

	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(storeController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
		store = new Store("1", 1.2, 2.4);
	}


	@Test
	public void testSaveStoreInfo() throws Exception {
		String storeRequest = mapper.writeValueAsString(store);
		this.mockMvc.perform(post("/stores").contentType(MediaType.APPLICATION_JSON)
				.content(storeRequest))
				.andExpect(status().isOk());
	}
	
}
