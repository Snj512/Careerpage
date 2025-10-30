package com.career.jobs.repository;

import com.career.jobs.model.Candidates;
import com.career.jobs.model.Job;
import com.career.jobs.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {
    boolean existsByJobAndCandidate(Job job, Candidates candidate);
    List<JobApplication> findByCandidate(Candidates candidate);

    List<JobApplication> findByJob(Job job);
}