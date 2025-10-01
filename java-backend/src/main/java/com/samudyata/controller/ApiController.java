package com.samudyata.controller;

import com.samudyata.dto.ApiResponse;
import com.samudyata.dto.AppointmentRequest;
import com.samudyata.dto.NewsletterRequest;
import com.samudyata.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/appointment")
    public ResponseEntity<ApiResponse> submitAppointment(@Valid @RequestBody AppointmentRequest request) {
        try {
            emailService.sendAppointmentEmail(request);
            return ResponseEntity.ok(new ApiResponse(true, "Appointment request sent successfully!"));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body(new ApiResponse(false, "Failed to send appointment request"));
        }
    }

    @PostMapping("/newsletter")
    public ResponseEntity<ApiResponse> subscribeNewsletter(@Valid @RequestBody NewsletterRequest request) {
        try {
            emailService.sendNewsletterEmail(request);
            return ResponseEntity.ok(new ApiResponse(true, "Successfully subscribed to newsletter!"));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body(new ApiResponse(false, "Failed to subscribe to newsletter"));
        }
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        return ResponseEntity.ok(Map.of(
            "status", "OK",
            "timestamp", LocalDateTime.now()
        ));
    }
}