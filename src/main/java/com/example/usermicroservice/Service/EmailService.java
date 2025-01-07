package com.example.usermicroservice.Service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
   private final JavaMailSender javaMailSender;

   public EmailService(JavaMailSender javaMailSender) {
      this.javaMailSender = javaMailSender;
   }
   /*@Async
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("emilycarter198512@gmail.com");

        javaMailSender.send(message);
    }
*/

    public void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // Replace this URL with the actual endpoint of your application
        String controllerUrl = "http://localhost:8080/email/send";

        String htmlContent = "<html>" +
                "<body>" +
                "<h1>" + subject + "</h1>" +
                "<p>" + text + "</p>" +
                "<a href='" + controllerUrl + "' " +
                "style='padding: 10px 20px; color: white; background-color: blue; text-decoration: none; border-radius: 5px;'>Click Here</a>" +
                "</body>" +
                "</html>";

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true); // Use the second parameter `true` to send as HTML
        helper.setFrom("emilycarter198512@gmail.com");

        javaMailSender.send(mimeMessage);
    }




}
