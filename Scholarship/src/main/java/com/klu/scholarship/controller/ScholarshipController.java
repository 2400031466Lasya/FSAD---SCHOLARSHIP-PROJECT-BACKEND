package com.klu.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.scholarship.entity.Scholarship;
import com.klu.scholarship.service.ScholarshipService;

import java.util.List;

@RestController
@RequestMapping("/api/scholarships")
@CrossOrigin("*")
public class ScholarshipController {

    @Autowired
    private ScholarshipService service;

    // ✅ CREATE (POST)
    @PostMapping
    public Scholarship addScholarship(@RequestBody Scholarship scholarship) {
        return service.save(scholarship);
    }

    // ✅ READ (GET ALL)
    @GetMapping
    public List<Scholarship> getAll() {
        return service.getAll();
    }

    // ✅ READ (GET BY ID)
    @GetMapping("/{id}")
    public Scholarship getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // ✅ UPDATE (PUT)
    @PutMapping("/{id}")
    public Scholarship update(@PathVariable Long id, @RequestBody Scholarship scholarship) {
        return service.update(id, scholarship);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted successfully";
    }
}