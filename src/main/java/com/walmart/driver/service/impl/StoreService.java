package com.walmart.driver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.driver.entity.Store;
import com.walmart.driver.repository.StoreDetailRepository;

@Service
public class StoreService{

	@Autowired
	private StoreDetailRepository storeDetailRepository;
	
	public Store saveStoreInfo(final Store store) {
		return storeDetailRepository.save(store);
	}
}
