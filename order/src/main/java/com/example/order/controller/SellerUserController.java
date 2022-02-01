package com.example.order.controller;

import com.example.order.config.ProjectUrlConfig;
import com.example.order.constant.CookieConstant;
import com.example.order.constant.RedisConstant;
import com.example.order.daoobject.SellerInfo;
import com.example.order.enums.ResultEnum;
import com.example.order.service.SellerService;
import com.example.order.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class SellerUserController {


    @Autowired
    SellerService sellerService;
    @Autowired
    ProjectUrlConfig projectUrlConfig;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openId") String openId, HttpServletResponse response, Map<String, Object> map) {

        SellerInfo sellerInfo = sellerService.findByOpenId(openId);
        if (null == sellerInfo) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), token, expire, TimeUnit.SECONDS);
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list", map);

    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {

        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);

        if (null != cookie) {
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}
