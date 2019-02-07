package com.invillia.acme.service;

import com.invillia.acme.model.Payment;
import com.invillia.acme.model.constant.OrderStatus;
import com.invillia.acme.model.constant.PaymentStatus;
import com.invillia.acme.repository.PaymentRepository;
import com.invillia.acme.service.amqp.PaymentSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentSender paymentSender;
    @Autowired
    private OrderService orderService;

    @Transactional
    public Payment requestPayment(Payment payment) {
        payment = paymentRepository.save(payment);

        paymentSender.send(payment);

        return payment;
    }

    @Transactional
    public void processPayment(Payment payment) {

        // Faz alguma regra complexa de pagamento

        updateStatus(payment);
    }

    private void updateStatus(Payment payment) {
        paymentRepository.findById(payment.getId())
                .ifPresent(payment1 -> {
                    payment.setStatus(PaymentStatus.APPROVED);
                    payment.getOrder().setStatus(OrderStatus.FINISHED);

                    paymentRepository.save(payment);
                });
    }
}
