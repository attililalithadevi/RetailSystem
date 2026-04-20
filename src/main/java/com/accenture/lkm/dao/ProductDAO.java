package com.accenture.lkm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.lkm.entity.ProductEntity;

public interface ProductDAO extends JpaRepository<ProductEntity, Integer> {

}