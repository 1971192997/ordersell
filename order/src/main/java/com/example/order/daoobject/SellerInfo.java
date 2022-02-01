package com.example.order.daoobject;

import ch.qos.logback.classic.db.names.ColumnName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class SellerInfo {

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) not null")
    private String userId;
    private String userName;
    private String password;
    private String openId;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date updateTime;

}
