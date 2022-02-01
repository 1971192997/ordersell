package com.example.order.aspect;

import com.example.order.constant.CookieConstant;
import com.example.order.constant.RedisConstant;
import com.example.order.exception.SellerAuthorizeException;
import com.example.order.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Slf4j
@Component
public class SellerAuthorizeAspect {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(public * com.example.order.controller.Seller*.*(..))" +
            "!execution(public * com.example.order.controller.SellerUser.*(..))")
    public void verify() {
    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (null == cookie) {
            log.error("doVerify cookie null");
            throw new SellerAuthorizeException();
        }

        String tokenValue = stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.error("doVerify redis token null");
            throw new SellerAuthorizeException();
        }
    }
}
