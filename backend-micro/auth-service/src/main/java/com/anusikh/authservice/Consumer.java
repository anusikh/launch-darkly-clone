package com.anusikh.authservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = "sample_queue")
    public void consumeMessageFromQueue(String message) {
        System.out.println("Message recieved from queue : " + message);
    }
}