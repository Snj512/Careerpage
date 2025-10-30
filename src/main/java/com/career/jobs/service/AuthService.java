package com.career.jobs.service;

import com.career.jobs.dto.*;
import com.career.jobs.model.*;
import com.career.jobs.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CandidateRepository candidateRepository;
    private final RecruiterRepository recruiterRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public String registerCandidate(RegisterCandidateDto dto) {
        if (candidateRepository.existsByEmailId(dto.getEmailId())) {
            throw new RuntimeException("Candidate already registered with email " + dto.getEmailId());
        }
        Candidates candidate = new Candidates();
        candidate.setFullName(dto.getFullName());
        candidate.setEmailId(dto.getEmailId());
        candidate.setPassword(passwordEncoder.encode(dto.getPassword()));
        candidate.setExperience(dto.getExperience());
        candidate.setSkills(dto.getSkills());
        candidate.setLocations(dto.getLocations());
        candidateRepository.save(candidate);

        return jwtService.generateToken(candidate.getEmailId(), Role.Candidate.name());
    }


    public String registerRecruiter(RegisterRecruiterDto dto) {
        // Step 1: Check for duplicate email
        if (recruiterRepository.existsByEmailId(dto.getEmailId())) {
            throw new RuntimeException("Recruiter already registered with email " + dto.getEmailId());
        }

        // Step 2: Validate creator (if provided)
        Recruiter createdBy = null;
        if (dto.getCreatedBy() != null) {
            createdBy = recruiterRepository.findById(dto.getCreatedBy().intValue())
                    .orElseThrow(() -> new RuntimeException("Admin recruiter not found"));

            if (createdBy.getRole() != Role.Admin) {
                throw new RuntimeException("Only Admin can create Recruiters");
            }
        }

        // Step 3: Manual mapping
        Recruiter recruiter = new Recruiter();
        recruiter.setFullName(dto.getFullName());
        recruiter.setCompany(dto.getCompany());
        recruiter.setEmailId(dto.getEmailId());
        recruiter.setPassword(passwordEncoder.encode(dto.getPassword()));

        // Step 4: Assign role
        if (dto.getRole() != null && dto.getRole().equalsIgnoreCase("Admin")) {
            recruiter.setRole(Role.Admin);
        } else {
            recruiter.setRole(Role.Recruiter);
        }

        // Step 5: Set creator (optional)
        recruiter.setCreatedBy(createdBy);

        // Step 6: Save and generate JWT
        recruiterRepository.save(recruiter);
        return jwtService.generateToken(recruiter.getEmailId(), recruiter.getRole().name());
    }


    public String login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // Identify which table the user belongs to
        String role;
        if (recruiterRepository.existsByEmailId(request.getEmail())) {
            Recruiter rec = recruiterRepository.findByEmailId(request.getEmail()).get();
            role = rec.getRole().name();
        } else {
            role = "Candidate";
        }

        return jwtService.generateToken(request.getEmail(), role);
    }
}
