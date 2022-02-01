package com.example.order.service.impl;

import com.example.order.daoobject.ProductCategory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    void findOne() {
        ProductCategory productCategory = categoryService.findOne(1);
        System.out.println("--------------productCategory "+productCategory.toString());
        Assert.assertNotNull(productCategory);
    }

    @Test
    void findAll() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    void findByCategoryTypeIn() {
        List<Integer> tp = Arrays.asList(1, 2, 3, 4);
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(tp);
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    void save() {
        ProductCategory productCategory = new ProductCategory("hello kity", 4);
        ProductCategory res = categoryService.save(productCategory);
        Assert.assertNotNull(res);
    }
}