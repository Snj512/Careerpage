package com.career.jobs.repository;

import com.career.jobs.model.Job;
import com.career.jobs.model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByPostedBy(Recruiter r);
    List<Job> findByRequiredSkillsContainingIgnoreCaseAndLocationContainingIgnoreCase(String skill, String location);
}