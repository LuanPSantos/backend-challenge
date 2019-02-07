package com.invillia.acme.controller;

import com.invillia.acme.model.Payment;
import com.invillia.acme.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Payment requestPayment(@RequestBody Payment payment){
        return paymentService.requestPayment(payment);
    }
}
