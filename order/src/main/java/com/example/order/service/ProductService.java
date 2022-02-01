package com.example.order.service;

import com.example.order.daoobject.ProductInfo;
import com.example.order.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);

    Page<ProductInfo> findAll(Pageable pageable);

    List<ProductInfo> findUpAll();


    ProductInfo save(ProductInfo productInfo);

    ProductInfo onSale(String productId);

    ProductInfo offSale(String productId);

    void increaseStock(List<CartDTO> cartDTOList);

    void decreaseStock(List<CartDTO> cartDTOList);


}
