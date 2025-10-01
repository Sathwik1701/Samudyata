package com.samudyata.service;

import com.samudyata.dto.AppointmentRequest;
import com.samudyata.dto.NewsletterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.email.to:info@samudyata.com}")
    private String toEmail;

    @Value("${app.email.from:${spring.mail.username}}")
    private String fromEmail;

    public void sendAppointmentEmail(AppointmentRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("New Appointment Request - Samudyata Infra Solutions");
        
        String emailBody = String.format(
            "New Appointment Request\n\n" +
            "Name: %s\n" +
            "Email: %s\n" +
            "Mobile: %s\n" +
            "Purpose: %s\n" +
            "Date: %s",
            request.getName(),
            request.getEmail(),
            request.getMobile(),
            request.getPurpose(),
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        
        message.setText(emailBody);
        mailSender.send(message);
    }

    public void sendNewsletterEmail(NewsletterRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("New Newsletter Subscription - Samudyata");
        
        String emailBody = String.format(
            "New Newsletter Subscription\n\n" +
            "Email: %s\n" +
            "Date: %s",
            request.getEmail(),
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        
        message.setText(emailBody);
        mailSender.send(message);
    }
}