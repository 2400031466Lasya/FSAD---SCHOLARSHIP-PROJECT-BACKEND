package com.klu.scholarship.repository;

import com.klu.scholarship.entity.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {
}