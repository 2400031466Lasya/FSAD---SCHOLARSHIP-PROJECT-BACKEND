package com.klu.scholarship.dto;

public class ApplicationResponseDTO {

    private Long id;
    private String status;
    private String userEmail;
    private String scholarshipName;
    private String studentName; // ✅ NEW

    public ApplicationResponseDTO(Long id, String status, String userEmail, String scholarshipName, String studentName) {
        this.id = id;
        this.status = status;
        this.userEmail = userEmail;
        this.scholarshipName = scholarshipName;
        this.studentName = studentName;
    }

    // GETTERS
    public Long getId() { return id; }
    public String getStatus() { return status; }
    public String getUserEmail() { return userEmail; }
    public String getScholarshipName() { return scholarshipName; }
    public String getStudentName() { return studentName; }
}