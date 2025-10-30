package com.career.jobs.security;

import com.career.jobs.model.Candidates;
import com.career.jobs.model.Recruiter;
import com.career.jobs.model.Role;
import com.career.jobs.repository.CandidateRepository;
import com.career.jobs.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final RecruiterRepository recruiterRepository;
    private final CandidateRepository candidateRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Try recruiter first
        Recruiter rec = recruiterRepository.findByEmailId(email).orElse(null);
        if (rec != null) {
            // Map your Role enum to ROLE_... format
            String roleName = rec.getRole() != null ? rec.getRole().name() : Role.Recruiter.name();
            String authority = "ROLE_" + roleName.toUpperCase();
            return new CustomUserDetails(
                    rec.getEmailId(),
                    rec.getPassword(),
                    List.of(new SimpleGrantedAuthority(authority))
            );
        }

        Candidates cand = candidateRepository.findByEmailId(email).orElse(null);
        if (cand != null) {
            return new CustomUserDetails(
                    cand.getEmailId(),
                    cand.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_CANDIDATE"))
            );
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
