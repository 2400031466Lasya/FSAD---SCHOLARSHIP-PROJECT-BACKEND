package com.klu.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.scholarship.dto.ApplicationRequestDTO;
import com.klu.scholarship.dto.ApplicationResponseDTO;
import com.klu.scholarship.service.ApplicationService;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:5173")
public class ApplicationController {

    @Autowired
    private ApplicationService service;

    // ✅ APPLY
    @PostMapping("/apply")
    public ApplicationResponseDTO apply(@RequestBody ApplicationRequestDTO dto) {
        return service.apply(dto);
    }

    // ✅ GET USER APPLICATIONS
    @GetMapping("/user/{userId}")
    public List<ApplicationResponseDTO> getByUser(@PathVariable Long userId) {
        return service.getByUser(userId);
    }

    // ✅ GET ALL (ADMIN)
    @GetMapping("/all")
    public List<ApplicationResponseDTO> getAll() {
        return service.getAll();
    }

    // 🔥 NEW: APPROVE APPLICATION
    @PutMapping("/approve/{id}")
    public ApplicationResponseDTO approve(@PathVariable Long id) {
        return service.updateStatus(id, "APPROVED");
    }

    // 🔥 NEW: REJECT APPLICATION
    @PutMapping("/reject/{id}")
    public ApplicationResponseDTO reject(@PathVariable Long id) {
        return service.updateStatus(id, "REJECTED");
    }
}