package com.example.order.repository;

import com.example.order.daoobject.ProductInfo;
import org.hibernate.type.StringNVarcharType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);
}
