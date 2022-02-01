package com.example.order.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class OrderForm {
    @NotEmpty
    private String name;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String openid;
    @NotEmpty
    private String address;
    @NotEmpty
    private String items;

}
