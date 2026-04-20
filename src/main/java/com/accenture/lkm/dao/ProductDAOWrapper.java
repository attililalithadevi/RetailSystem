package com.accenture.lkm.dao;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.lkm.bean.ProductBean;
import com.accenture.lkm.entity.ProductEntity;

@Repository
public class ProductDAOWrapper {
	@Autowired
	private ProductDAO productDAO;

	public ProductBean getProductDetailsById(Integer productId) {
		ProductEntity entity = productDAO.findById(productId).orElse(null);
		if (entity != null) {
			ProductBean bean = new ProductBean();
			BeanUtils.copyProperties(entity, bean);
			return bean;
		}
		return null;
	}

	public void updateProductStock(ProductBean productBean) {
		ProductEntity entity = productDAO.findById(productBean.getProductId()).get();
		entity.setStock(productBean.getStock());
		productDAO.save(entity);
	}
}