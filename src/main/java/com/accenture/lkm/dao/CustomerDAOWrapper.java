package com.accenture.lkm.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.lkm.bean.CustomerBean;
import com.accenture.lkm.entity.CustomerEntity;

@Repository
public class CustomerDAOWrapper {
	@Autowired
	private CustomerDAO customerDAO;

	public List<CustomerBean> getCustomerDetailsByType(String customerType) {
		List<CustomerEntity> entities = customerDAO.getCustomerDetailsByType(customerType);
		List<CustomerBean> beans = new ArrayList<>();
		for (CustomerEntity entity : entities) {
			CustomerBean bean = new CustomerBean();
			BeanUtils.copyProperties(entity, bean);
			beans.add(bean);
		}
		return beans;
	}

	public CustomerBean getCustomerDetailsById(Integer customerId) {
		CustomerEntity entity = customerDAO.findById(customerId).orElse(null);
		if (entity != null) {
			CustomerBean bean = new CustomerBean();
			BeanUtils.copyProperties(entity, bean);
			return bean;
		}
		return null;
	}

	public void updateCustomerType(CustomerBean customerBean) {
		CustomerEntity entity = customerDAO.findById(customerBean.getCustomerId()).get();
		entity.setCustomerType(customerBean.getCustomerType());
		customerDAO.save(entity);
	}
}
