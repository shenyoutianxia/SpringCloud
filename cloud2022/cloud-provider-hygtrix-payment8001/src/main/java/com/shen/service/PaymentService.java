package com.shen.service;


import org.springframework.web.bind.annotation.PathVariable;

public interface PaymentService {

    public String paymentInfo_ok(Integer id);

    public String paymentInfo_timeOut(Integer id);

    public String paymentInfo_TimeOutHandler(Integer id);

    public String paymentCircuitBreaker(Integer id);

    public String paymentCircuitBreaker_fallback(Integer id);
}
