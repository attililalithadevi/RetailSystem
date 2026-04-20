package com.accenture.lkm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // Don't forget this import!

import com.accenture.lkm.entity.CustomerEntity;

public interface CustomerDAO extends JpaRepository<CustomerEntity, Integer> {
	@Query("SELECT c FROM CustomerEntity c WHERE c.customerType = ?1")
	List<CustomerEntity> getCustomerDetailsByType(String customerType);
}