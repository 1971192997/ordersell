package com.example.order.service.impl;

import com.example.order.exception.SellException;
import com.example.order.service.RedisLock;
import com.example.order.service.SecKillService;
import com.example.order.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SecKillServiceImpl implements SecKillService {

    private static final int TIMEOUT = 10 * 1000;
    @Autowired
    RedisLock redisLock;

    //in memory to mock sec kill
    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;

    static {
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456", 100000);
        stock.put("123456", 100000);
    }

    private String queryMap(String productId) {
        return "sec kill total : "
                + products.get(productId)
                + ", remain : "
                + stock.get(productId)
                + ", current user order : "
                + orders.size()
                + ".";
    }

    @Override
    public String querySecKillProductInfo(String productId) {
        return this.queryMap(productId);
    }

//    @Override //solo, we use synchronized keyword
//    public synchronized void orderProductMockDiffUser(String productId) {
//
//        int stockNum = stock.get(productId);
//        if (stockNum == 0) {
//            throw new SellException(100, "the end");
//        } else {
//            orders.put(KeyUtil.genUniqueKey(), productId);
//            stockNum = stockNum - 1;
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            stock.put(productId, stockNum);
//        }
//
//    }

    @Override //redis lock in distribute scene
    public void orderProductMockDiffUser(String productId) {

        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(productId, String.valueOf(time))) {
            throw new SellException(101, "crowed, pls retry later");
        }
        int stockNum = stock.get(productId);
        if (stockNum == 0) {
            throw new SellException(100, "the end");
        } else {
            orders.put(KeyUtil.genUniqueKey(), productId);
            stockNum = stockNum - 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }
        redisLock.unlock(productId, String.valueOf(time));

    }
}
