package com.career.jobs.repository;

import com.career.jobs.model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruiterRepository extends JpaRepository<Recruiter, Integer> {
    Optional<Recruiter> findByEmail(String email);
    boolean existsByEmail(String email);
}