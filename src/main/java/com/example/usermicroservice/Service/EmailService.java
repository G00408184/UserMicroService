package com.example.usermicroservice.Service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
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

   public void sendEmail(String link, String subject, String content, String timestamp) throws MessagingException {
       System.out.println("It reached it");
       MimeMessage mimeMessage = javaMailSender.createMimeMessage();
       MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

       // Cleaned up and fixed HTML email content
       String htmlContent = "<html>" +
               "<body style='font-family: Arial, sans-serif; line-height: 1.5;'>" +
               "<h2 style='color: #007BFF;'>" + subject + "</h2>" +
               "<p style='font-size: 16px;'>" + content + "</p>" +
               "<p><strong>Timestamp:</strong> " + timestamp + "</p>" +
               "<p>You can manage the loan extension using the link below:</p>" +
               "<p><a href='" + link + "' " +
               "style='padding: 10px 15px; color: white; background-color: blue; text-decoration: none; border-radius: 5px;'>Approve Loan Extension</a></p>" +
               "</body>" +
               "</html>";

       // Send the email with the dynamic link and provided data
       helper.setTo("rayanzyad99@gmail.com");
       helper.setSubject(subject);
       helper.setText(htmlContent, true);
       helper.setFrom("emilycarter198512@gmail.com");
       System.out.println("It reached it");

       javaMailSender.send(mimeMessage);
   }
    public void SendEmailToUser(String recipientEmail, String subject, String text) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        String htmlContent = "<html>" +
                "<body style='font-family: Arial, sans-serif; line-height: 1.5; color: #333; padding: 20px;'>" +
                "<div style='max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 8px; background-color: #ffffff;'>" +
                "<h2 style='color: #007BFF; text-align: center; margin-bottom: 20px;'>" + subject + "</h2>" +
                "<p style='font-size: 16px;'>" + text + "</p>" +
                "<p style='font-size: 14px; color: #555; margin-top: 20px;'>Thank you for using our library services.</p>" +
                "<p style='font-size: 14px; color: #555;'>If you have any questions, please contact us at <a href='mailto:support@example.com' style='color: #007BFF;'>support@example.com</a>.</p>" +
                "</div>" +
                "</body>" +
                "</html>";

        helper.setTo(recipientEmail);
        helper.setSubject(subject);
        helper.setText(htmlContent, true); // Enable HTML support
        helper.setFrom("emilycarter198512@gmail.com");

        // Send the email
        javaMailSender.send(mimeMessage);
    }


}
