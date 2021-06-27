package com.walmart.driver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.driver.entity.Store;
import com.walmart.driver.service.impl.StoreService;

@RestController
@RequestMapping("/stores")
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	@PostMapping
	public Store saveStore(@RequestBody Store store) {
		return storeService.saveStoreInfo(store);
	}

}
