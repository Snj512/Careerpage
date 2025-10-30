package com.career.jobs.service;

import com.career.jobs.model.job;
import com.career.jobs.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CareerService {

    private final JobRepository jobRepository;

    public List<job> findAll() {
        return jobRepository.findAll();
    }
}
