package com.career.jobs.controller;

import com.career.jobs.dto.*;
import com.career.jobs.service.CandidateJobService;
import com.career.jobs.service.CandidateProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CandidateJobController {

    private final CandidateJobService candidateJobService;
    private final CandidateProfileService candidateProfileService;

    //PUBLIC: List all jobs
    @GetMapping("/jobs")
    public ResponseEntity<List<PublicJobDto>> getAllJobs() {
        return ResponseEntity.ok(candidateJobService.getAllJobs());
    }

    //PUBLIC: View job details
    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobDetailDto> getJobDetails(@PathVariable Integer id) {
        return ResponseEntity.ok(candidateJobService.getJobDetails(id));
    }

    //CANDIDATE: Apply for a job
    @PreAuthorize("hasRole('CANDIDATE')")
    @PostMapping("/jobs/{id}/apply")
    public ResponseEntity<ApiResponse> applyForJob(@PathVariable Integer id) {
        return ResponseEntity.ok(candidateJobService.applyForJob(id));
    }

    //CANDIDATE: View all applied jobs
    @PreAuthorize("hasRole('CANDIDATE')")
    @GetMapping("/jobs/applied")
    public ResponseEntity<List<JobDetailDto>> getAppliedJobs() {
        return ResponseEntity.ok(candidateJobService.getAppliedJobs());
    }

    //profile
    @PreAuthorize("hasRole('CANDIDATE')")
    @GetMapping("/candidates/profile")
    public ResponseEntity<CandidateProfileDto> getProfile() {
        return ResponseEntity.ok(candidateProfileService.getProfile());
    }

    //Update own profile
    @PreAuthorize("hasRole('CANDIDATE')")
    @PutMapping("/candidates/profile")
    public ResponseEntity<ApiResponse> updateProfile(@RequestBody CandidateProfileDto profileDto) {
        return ResponseEntity.ok(candidateProfileService.updateProfile(profileDto));
    }
}
