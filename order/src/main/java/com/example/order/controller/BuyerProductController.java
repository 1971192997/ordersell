package com.example.order.controller;

import com.example.order.VO.ProductInfoVO;
import com.example.order.VO.ProductVO;
import com.example.order.VO.ResultVO;
import com.example.order.daoobject.ProductCategory;
import com.example.order.daoobject.ProductInfo;
import com.example.order.service.CategoryService;
import com.example.order.service.ProductService;
import com.example.order.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/findOne")
    public ResultVO<ProductInfo> findOne(@RequestParam("productId") String productId) {

        ProductInfo productInfo = productService.findOne(productId);
        System.out.println("==================fuck==" + productInfo.toString());
        return ResultVOUtil.success(productInfo);
    }

    @RequestMapping("/findCategory")
    public ResultVO findOneCategory(@RequestParam("categoryId") Integer categoryId) {

        ProductCategory productCategory = categoryService.findOne(categoryId);
        System.out.println("==================fuck==" + productCategory.toString());
        return ResultVOUtil.success(productCategory);
    }

    @RequestMapping("/list")
    @Cacheable(cacheNames = "product", key = "123", unless = "result.getCode()==0")//cache condition
    public ResultVO list() {

        List<ProductInfo> productInfoList = productService.findUpAll();

        List<Integer> categoryList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryList);

        List<ProductVO> productVOList = new ArrayList<ProductVO>();

        for (ProductCategory productCategory : productCategoryList) {

            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<ProductInfoVO>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }

            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }

}
