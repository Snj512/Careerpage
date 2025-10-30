package com.career.jobs.repository;

import com.career.jobs.model.Candidates;
import com.career.jobs.model.Job;
import com.career.jobs.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface JobapplicationRepository extends JpaRepository<JobApplication, Integer> {    List<JobApplication> findByJob(Job job);
    List<JobApplication> findByCandidate(Candidates candidate);
    Optional<JobApplication> findByJobAndCandidate(Job job, Candidates candidate);

}