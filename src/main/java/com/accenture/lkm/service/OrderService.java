package com.accenture.lkm.service;

import java.util.List;

import com.accenture.lkm.bean.OrderBean;

public interface OrderService {
	List<OrderBean> getOrderDetailsByCustomerId(Integer customerId);

	List<OrderBean> getOrderDetailsByCustomerTypeAndBillInRange(String type, double min, double max);
}