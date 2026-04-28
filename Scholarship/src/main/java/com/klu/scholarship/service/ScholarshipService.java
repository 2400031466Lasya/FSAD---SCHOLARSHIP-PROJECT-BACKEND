package com.klu.scholarship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.scholarship.entity.Scholarship;
import com.klu.scholarship.repository.ScholarshipRepository;

import java.util.List;

@Service
public class ScholarshipService {

    @Autowired
    private ScholarshipRepository repo;

    public Scholarship save(Scholarship scholarship) {
        return repo.save(scholarship);
    }

    public List<Scholarship> getAll() {
        return repo.findAll();
    }

    public Scholarship getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Scholarship update(Long id, Scholarship scholarship) {
        scholarship.setId(id);
        return repo.save(scholarship);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}