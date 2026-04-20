package com.accenture.lkm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.bean.OrderBean;
import com.accenture.lkm.service.OrderService;

@RestController
@RequestMapping("/order/controller")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@GetMapping(value = "/getOrderDetailsByCustomerId/{customerId}")
	public ResponseEntity<List<OrderBean>> getByCustId(@PathVariable Integer customerId) {
		List<OrderBean> orders = orderService.getOrderDetailsByCustomerId(customerId);
		return orders == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@GetMapping(value = "/getOrderDetailsByCustomerTypeAndBillInRange/{customerType}--{min}--{max}")
	public ResponseEntity<List<OrderBean>> getByRange(@PathVariable String customerType, @PathVariable Double min,
			@PathVariable Double max) {
		List<OrderBean> orders = orderService.getOrderDetailsByCustomerTypeAndBillInRange(customerType, min, max);
		if (orders == null || orders.isEmpty()) {
			return new ResponseEntity<List<OrderBean>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<OrderBean>>(orders, HttpStatus.OK);
	}
}