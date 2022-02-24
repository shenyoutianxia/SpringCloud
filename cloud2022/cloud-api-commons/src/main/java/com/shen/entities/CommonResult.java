package com.shen.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;  //响应码
    private String message; //响应消息
    private T data;  //响应数据

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
