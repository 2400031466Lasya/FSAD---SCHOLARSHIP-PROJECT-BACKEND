package com.klu.scholarship.entity;

import jakarta.persistence.*;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @ManyToOne
    private User user;

    @ManyToOne
    private Scholarship scholarship;

    // ✅ GETTERS
    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public User getUser() {          // ✅ ADD THIS
        return user;
    }

    public Scholarship getScholarship() {   // ✅ ADD THIS
        return scholarship;
    }

    // ✅ SETTERS
    public void setUser(User user) {
        this.user = user;
    }

    public void setScholarship(Scholarship scholarship) {
        this.scholarship = scholarship;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}