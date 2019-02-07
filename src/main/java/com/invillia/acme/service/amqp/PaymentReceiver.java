package com.invillia.acme.service.amqp;

import com.invillia.acme.model.Payment;
import com.invillia.acme.service.PaymentService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@RabbitListener(queues = "paymentQueue")
public class PaymentReceiver {

    @Autowired
    private PaymentService paymentService;

    @RabbitHandler
    public void receive(Payment payment) {
        paymentService.processPayment(payment);
    }
}
