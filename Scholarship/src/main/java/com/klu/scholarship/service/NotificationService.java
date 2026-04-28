package com.klu.scholarship.service;

import com.klu.scholarship.entity.Notification;

import com.klu.scholarship.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repo;

    public void notifyUser(Long userId, String msg) {
        Notification n = new Notification();
        n.setUserId(userId);
        n.setMessage(msg);
        n.setSeen(false);
        repo.save(n);
    }
}