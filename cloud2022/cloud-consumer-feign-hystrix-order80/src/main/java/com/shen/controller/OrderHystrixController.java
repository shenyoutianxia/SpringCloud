package com.shen.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shen.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//全局服务降低
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){

        String result = paymentHystrixService.paymentInfo_ok(id);
        return result;
    }


    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {   //独立的服务降低
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand  //全局的服务降低
    public String paymentInfo_timeOut(@PathVariable("id") Integer id){

        String result = paymentHystrixService.paymentInfo_timeOut(id);
        return result;
    }

    public String paymentTimeOutFallbackMethod(Integer id) {
        return "我是消费者80，对方支付系统繁忙请稍后再试！！";
    }

    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试！！";
    }
}
