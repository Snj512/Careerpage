package com.career.jobs.repository;

import com.career.jobs.model.candidates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepo extends JpaRepository<candidates, Integer> {
}
