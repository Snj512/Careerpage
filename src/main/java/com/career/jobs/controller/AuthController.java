package com.career.jobs.controller;


import com.career.jobs.dto.*;
import com.career.jobs.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/candidate")
    public ResponseEntity<AuthResponse> registerCandidate(@RequestBody RegisterCandidateDto dto) {
        String token = authService.registerCandidate(dto);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register/recruiter")
    public ResponseEntity<AuthResponse> registerRecruiter(@RequestBody RegisterRecruiterDto dto) {
        String token = authService.registerRecruiter(dto);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
