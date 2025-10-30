package com.career.jobs.service;

import com.career.jobs.dto.ApiResponse;
import com.career.jobs.dto.CandidateProfileDto;
import com.career.jobs.model.Candidates;
import com.career.jobs.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateProfileService {

    private final CandidateRepository candidateRepository;

    public CandidateProfileDto getProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Candidates candidate = candidateRepository.findByEmailId(email)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        CandidateProfileDto dto = new CandidateProfileDto();
        dto.setFullName(candidate.getFullName());
        dto.setEmailId(candidate.getEmailId());
        dto.setExperience(candidate.getExperience());
        dto.setSkills(candidate.getSkills());
        dto.setLocations(candidate.getLocations());
        return dto;
    }

    public ApiResponse updateProfile(CandidateProfileDto dto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Candidates candidate = candidateRepository.findByEmailId(email)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        candidate.setFullName(dto.getFullName());
        candidate.setExperience(dto.getExperience());
        candidate.setSkills(dto.getSkills());
        candidate.setLocations(dto.getLocations());

        candidateRepository.save(candidate);
        return new ApiResponse("success", "Profile updated successfully");
    }
}
