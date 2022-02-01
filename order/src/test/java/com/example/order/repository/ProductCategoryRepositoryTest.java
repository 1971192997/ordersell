package com.example.order.repository;

import com.example.order.daoobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    ProductCategoryRepository repository;

    @Test
    public void getOneTest() {
        ProductCategory productCategory = repository.getById(1);
        System.out.println("--------------------------------");
        System.out.println(productCategory.getCategoryName());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = repository.findAll();
        System.out.println("+++++++++++++++++");
        System.out.println(productCategoryList.size());
    }

    @Test
    public void save() {

        ProductCategory productCategory = new ProductCategory("tools", 2);
        ProductCategory p = repository.save(productCategory);
        Assert.assertNotNull(p);
        System.out.println(p.toString());

    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> tp = Arrays.asList(2, 3, 4);
        List<ProductCategory> rl = repository.findByCategoryTypeIn(tp);
        Assert.assertNotEquals(0, rl.size());
    }

    @Test
    public void updateTest(){
        ProductCategory productCategory = repository.getById(1);
//        ProductCategory productCategory = new ProductCategory("favo", 3);
        productCategory.setCategoryType(22);
        ProductCategory p = repository.save(productCategory);
        Assert.assertEquals(productCategory, p);
    }



}