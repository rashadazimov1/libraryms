package com.project.libraryms.rabbitmq.producer;

import com.project.libraryms.entities.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private final RabbitTemplate rabbitTemplate;
    @Value("${sample.rabbitmq.routingKey}")
    String routingKey;
    @Value("${sample.rabbitmq.exchange}")
    String exchange;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendQueue(User userDto) {
        System.out.println("Notification Sent: User  created :" + userDto.getUsername());
        rabbitTemplate.convertAndSend(exchange, routingKey, userDto);


    }
}
