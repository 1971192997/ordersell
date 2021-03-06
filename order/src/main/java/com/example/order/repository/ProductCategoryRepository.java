package com.example.order.repository;

import com.example.order.daoobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory>  findByCategoryTypeIn(List<Integer> typeList );
}
