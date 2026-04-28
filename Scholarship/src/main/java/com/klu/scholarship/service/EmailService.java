package com.klu.scholarship.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    // ✅ Dummy method (no email sending)
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Email service disabled");
    }
}