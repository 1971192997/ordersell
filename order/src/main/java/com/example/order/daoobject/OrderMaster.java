package com.example.order.daoobject;

import com.example.order.enums.OrderStatusEnum;
import com.example.order.enums.PayStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class OrderMaster {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date createTime;
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date updateTime;

}