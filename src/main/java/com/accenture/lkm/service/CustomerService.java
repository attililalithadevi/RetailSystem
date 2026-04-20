package com.accenture.lkm.service;

import java.util.List;

import com.accenture.lkm.bean.CustomerBean;

public interface CustomerService {
	List<CustomerBean> getCustomerDetailsByType(String customerType);

	CustomerBean updateCustomerType(CustomerBean customerBean);
}