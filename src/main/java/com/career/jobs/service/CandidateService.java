package com.career.jobs.service;

import com.career.jobs.dto.RegisterCandidateDto;
import com.career.jobs.repository.CandidateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor


@Service
public class CandidateService {

    private final CandidateRepo candidateRepo;

    public boolean findByEmail(String emailId) {
     return candidateRepo.existsByEmail(emailId);
    }

    public void save(RegisterCandidateDto registerCandidateDto) {
        candidateRepo.save()
    }
}
