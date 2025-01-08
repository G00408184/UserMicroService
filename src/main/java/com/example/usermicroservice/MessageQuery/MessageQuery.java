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
        switch (message.getContent()) {
            case "SendEmailToUser":
                emailService.SendEmailToUser(
                        message.getEmail(),
                        "Book is Expired",
                        "Please return the book to avoid additional charges."
                );
                System.out.println("Email sent to: " + message.getEmail());
                break;


            case "ExtendLoan":
                String loanId = message.getType(); // Assuming loanId is stored in the 'type' field
                String link = "http://localhost:8080/api/loans/PermissionGranted/" + loanId;

                emailService.sendEmail(
                        link,
                        "Loan Extension Request",
                        "A loan extension has been requested for loan ID: " + loanId,
                        message.getTimestamp()
                );
                break;
            case "Book":
                String bookId = message.getType(); // Assuming bookId is stored in the 'type' field
                String bookLink = "http://localhost:8083/books/addGranted/" + bookId;

                emailService.sendEmail(
                        bookLink,
                        "Book Addition Request",
                        "A book has been added with the ID: " + bookId, // Fixed to show the bookId instead of the link
                        message.getTimestamp()
                );
                // Logic for booking
                System.out.println("Booking for: " + message.getEmail());
                break;

            default:
                System.out.println("Unknown message type: " + message.getContent());
        }
    } catch (Exception e) {
        System.err.println("Error processing message: " + e.getMessage());
        throw new RuntimeException(e);
    }

    System.out.println("Received message: " + message);
    }
}
