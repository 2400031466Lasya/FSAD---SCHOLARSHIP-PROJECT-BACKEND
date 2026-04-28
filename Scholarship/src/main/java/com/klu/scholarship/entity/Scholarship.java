package com.klu.scholarship.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Scholarship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    private String description;
    private String provider;
    private Double amount;
    private LocalDate deadline;

    // GETTERS

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getProvider() {
        return provider;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    // SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}