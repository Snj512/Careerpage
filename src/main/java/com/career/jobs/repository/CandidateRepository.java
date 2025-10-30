package com.career.jobs.repository;

import com.career.jobs.model.Candidates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidates, Integer> {
    Optional<Candidates> findByEmailId(String emailId);
    boolean existsByEmailId(String emailId);
}
