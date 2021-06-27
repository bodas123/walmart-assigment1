package com.walmart.driver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walmart.driver.entity.Store;

@Repository
public interface StoreDetailRepository extends JpaRepository<Store, String>{
}
