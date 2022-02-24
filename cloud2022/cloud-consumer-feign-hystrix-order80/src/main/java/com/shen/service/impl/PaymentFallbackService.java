package com.shen.service.impl;

import com.shen.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "--------PaymentFallbackService fall back-paymentInfo_ok,😄";
    }

    @Override
    public String paymentInfo_timeOut(Integer id) {
        return "--------PaymentFallbackService fall back-paymentInfo_timeOut,┭┮﹏┭┮";
    }
}
