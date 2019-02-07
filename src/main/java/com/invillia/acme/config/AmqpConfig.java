package com.invillia.acme.config;

import com.invillia.acme.service.amqp.PaymentReceiver;
import com.invillia.acme.service.amqp.PaymentSender;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    @Bean
    public Queue paymentQueue() {
        return new Queue("paymentQueue");
    }

    @Bean
    public PaymentReceiver receiver() {
        return new PaymentReceiver();
    }

    @Bean
    public PaymentSender sender() {
        return new PaymentSender();
    }
}
