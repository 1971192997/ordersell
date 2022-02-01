package com.example.order.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = 6304814119471417871L;
    private Integer code;
    private String message;
    private T data;
}
