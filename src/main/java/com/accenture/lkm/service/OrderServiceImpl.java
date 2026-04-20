package com.accenture.lkm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.bean.OrderBean;
import com.accenture.lkm.dao.OrderDAOWrapper;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAOWrapper orderDAOWrapper;

	@Override
	public List<OrderBean> getOrderDetailsByCustomerId(Integer customerId) {
		List<OrderBean> orders = orderDAOWrapper.getOrderDetailsByCustomerId(customerId);

		// Return null if no orders found to help the controller trigger 404
		if (orders == null || orders.isEmpty()) {
			return null;
		}
		return orders;
	}

	@Override
	public List<OrderBean> getOrderDetailsByCustomerTypeAndBillInRange(String type, double min, double max) {
		return orderDAOWrapper.getOrderDetailsByCustomerTypeAndBillInRange(type, min, max);
	}

}