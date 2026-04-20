package com.accenture.lkm.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.lkm.bean.OrderBean;
import com.accenture.lkm.entity.OrderEntity;

@Repository
public class OrderDAOWrapper {
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private ProductDAO productDAO;

	public List<OrderBean> getOrderDetailsByCustomerId(Integer customerId) {
		return convertToBeanList(orderDAO.findByCustomerId(customerId));
	}

	public List<OrderBean> getOrderDetailsByCustomerTypeAndBillInRange(String type, double min, double max) {
		return convertToBeanList(orderDAO.getOrderDetailsByCustomerTypeAndBillInRange(type, min, max));
	}

	private List<OrderBean> convertToBeanList(List<OrderEntity> entities) {
		List<OrderBean> beans = new ArrayList<>();
		for (OrderEntity entity : entities) {
			OrderBean bean = new OrderBean();
			BeanUtils.copyProperties(entity, bean);
			customerDAO.findById(entity.getCustomerId()).ifPresent(c -> bean.setCustomerEmail(c.getCustomerEmail()));
			productDAO.findById(entity.getProductId()).ifPresent(p -> bean.setProductName(p.getProductName()));
			beans.add(bean);
		}
		return beans;
	}
}