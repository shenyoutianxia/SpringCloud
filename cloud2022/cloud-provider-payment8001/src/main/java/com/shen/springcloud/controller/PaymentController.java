package com.shen.springcloud.controller;

import com.shen.entities.CommonResult;
import com.shen.entities.Payment;
import com.shen.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){

        int result = paymentService.create(payment);
        log.info("*****插入结果："+result);
        if (result>0){

            return new CommonResult(200,"插入数据成功,serverPort:"+serverPort,result);
        }
        else {

            return new CommonResult(404,"插入数据失败",null);
        }
    }


    @GetMapping("/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){

        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果："+payment);
        if (payment!=null){

            return new CommonResult(200,"查询数据成功,serverPort:"+serverPort,payment);
        }
        else {

            return new CommonResult(404,"查询数据失败",null);
        }
    }
}
