package com.invillia.acme.service.amqp;

import com.invillia.acme.model.Payment;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentSender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    public void send(Payment payment) {
        this.template.convertAndSend(queue.getName(), payment);
    }
}
