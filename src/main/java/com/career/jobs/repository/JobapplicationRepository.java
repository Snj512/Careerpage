package com.career.jobs.repository;

import com.career.jobs.model.Jobapplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobapplicationRepository extends JpaRepository<Jobapplication, Integer> {
}