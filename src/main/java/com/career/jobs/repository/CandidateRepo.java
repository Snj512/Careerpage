package com.career.jobs.repository;

import com.career.jobs.model.Candidates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepo extends JpaRepository<Candidates, Integer> {
    Optional<Candidates> findByEmail(String email);
    boolean existsByEmail(String email);
}
