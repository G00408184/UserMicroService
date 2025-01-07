package com.example.usermicroservice.MessageQuery;

import com.example.usermicroservice.Service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageQuery {
    @Autowired
    private EmailService emailService;

@RabbitListener(queues = "productQueue")
public void receiveMessage(Message message)  {
    try {
        // Message should contain the local host link and email content and content
        emailService.sendEmail("rayanzyad99@gmail.com",
                " Queue Spring Boot Email with Button",
                "This is a test email with a clickable button. Click the button below to visit our website.");
    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }

    System.out.println("Received message: " + message);
    }
}
