package com.accenture.lkm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.accenture.lkm.entity.OrderEntity;

public interface OrderDAO extends JpaRepository<OrderEntity, Integer> {
	List<OrderEntity> findByCustomerId(Integer customerId);

	@Query("SELECT o FROM OrderEntity o, CustomerEntity c WHERE o.customerId = c.customerId "
			+ "AND c.customerType = :type AND o.billingAmount BETWEEN :min AND :max")
	List<OrderEntity> getOrderDetailsByCustomerTypeAndBillInRange(@Param("type") String type, @Param("min") double min,
			@Param("max") double max);
}