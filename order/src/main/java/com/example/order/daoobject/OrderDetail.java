package com.example.order.daoobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class OrderDetail {

    @Id
    private String detailId;
    private String orderId;
    private String productId;
    private String productName;
    private String productIcon;
    private BigDecimal productPrice;
    private Integer productQuantity;

    private Date createTime;
    private Date updateTime;

}
