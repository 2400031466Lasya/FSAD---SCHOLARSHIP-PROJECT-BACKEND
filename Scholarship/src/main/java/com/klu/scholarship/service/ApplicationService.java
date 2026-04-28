package com.klu.scholarship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.scholarship.dto.ApplicationRequestDTO;
import com.klu.scholarship.dto.ApplicationResponseDTO;
import com.klu.scholarship.entity.Application;
import com.klu.scholarship.entity.User;
import com.klu.scholarship.entity.Scholarship;
import com.klu.scholarship.exception.ResourceNotFoundException;
import com.klu.scholarship.repository.ApplicationRepository;
import com.klu.scholarship.repository.UserRepository;
import com.klu.scholarship.repository.ScholarshipRepository;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository appRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ScholarshipRepository schRepo;

    // ❌ REMOVED EmailService

    @Autowired
    private NotificationService notificationService;

    // ✅ APPLY FOR SCHOLARSHIP
    public ApplicationResponseDTO apply(ApplicationRequestDTO dto) {

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Scholarship scholarship = schRepo.findById(dto.getScholarshipId())
                .orElseThrow(() -> new ResourceNotFoundException("Scholarship not found"));

        Application app = new Application();
        app.setUser(user);
        app.setScholarship(scholarship);
        app.setStatus("PENDING");

        Application saved = appRepo.save(app);

        // 🔔 Notification only (email removed)
        notificationService.notifyUser(
                user.getId(),
                "Application submitted successfully"
        );

        return new ApplicationResponseDTO(
                saved.getId(),
                saved.getStatus(),
                user.getEmail(),
                scholarship.getTitle(),
                user.getName()
        );
    }

    // ✅ GET APPLICATIONS BY USER
    public List<ApplicationResponseDTO> getByUser(Long userId) {

        List<Application> apps = appRepo.findByUserId(userId);

        return apps.stream()
                .filter(app -> app.getUser() != null && app.getScholarship() != null)
                .map(app -> new ApplicationResponseDTO(
                        app.getId(),
                        app.getStatus(),
                        app.getUser().getEmail(),
                        app.getScholarship().getTitle(),
                        app.getUser().getName()
                ))
                .toList();
    }

    // ✅ GET ALL APPLICATIONS (ADMIN)
    public List<ApplicationResponseDTO> getAll() {

        List<Application> apps = appRepo.findAll();

        return apps.stream()
                .filter(app -> app.getUser() != null && app.getScholarship() != null)
                .map(app -> new ApplicationResponseDTO(
                        app.getId(),
                        app.getStatus(),
                        app.getUser().getEmail(),
                        app.getScholarship().getTitle(),
                        app.getUser().getName()
                ))
                .toList();
    }

    // ✅ APPROVE / REJECT APPLICATION
    public ApplicationResponseDTO updateStatus(Long id, String status) {

        Application app = appRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        app.setStatus(status);

        Application updated = appRepo.save(app);

        // 🔔 Notification only (email removed)
        notificationService.notifyUser(
                app.getUser().getId(),
                "Your application status changed to " + status
        );

        return new ApplicationResponseDTO(
                updated.getId(),
                updated.getStatus(),
                updated.getUser().getEmail(),
                updated.getScholarship().getTitle(),
                updated.getUser().getName()
        );
    }
}