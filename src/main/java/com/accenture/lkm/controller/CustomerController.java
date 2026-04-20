package com.accenture.lkm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.bean.CustomerBean;
import com.accenture.lkm.service.CustomerService;

@RestController
@RequestMapping("/customer/controller")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@GetMapping("/getCustomersByType/{type}")
	public List<CustomerBean> getCustomersByType(@PathVariable("type") String type) {
		List<CustomerBean> list = customerService.getCustomerDetailsByType(type);
		if (list == null || list.isEmpty()) {
			throw new org.springframework.web.server.ResponseStatusException(
					org.springframework.http.HttpStatus.NOT_FOUND, "No Customers Found");
		}
		return list;
	}

	@PutMapping("/updateCustomer")
	public ResponseEntity<String> updateCustomerType(@RequestBody CustomerBean customerBean) {
		CustomerBean updatedBean = customerService.updateCustomerType(customerBean);
		if (updatedBean == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String message = updatedBean.getCustomerEmail() + ", customer type is updated as "
				+ updatedBean.getCustomerType() + " successfully.";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}