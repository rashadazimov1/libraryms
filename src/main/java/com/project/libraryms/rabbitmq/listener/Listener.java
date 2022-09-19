package com.project.libraryms.rabbitmq.listener;

import com.project.libraryms.dto.bookdto.BookDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Listener {
    @RabbitListener(queues = "firstStepQueue")
    public void handlerMessage(BookDto bookDto) {
        System.out.println("Message received");
        System.out.println(bookDto.toString());

    }
}
