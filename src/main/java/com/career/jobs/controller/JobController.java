package com.career.jobs.controller;

import com.career.jobs.dto.ApplicantDto;
import com.career.jobs.dto.JobRequestDto;
import com.career.jobs.dto.JobResponseDto;
import com.career.jobs.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    // Recruiter creates a new job
    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<JobResponseDto> createJob(@RequestBody JobRequestDto dto) {
        return ResponseEntity.ok(jobService.createJob(dto));
    }

    // Recruiter gets all their own posted jobs
    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    @GetMapping("/my-jobs")
    public ResponseEntity<List<JobResponseDto>> getMyJobs() {
        return ResponseEntity.ok(jobService.getMyJobs());
    }

    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    @GetMapping("/{jobId}/applicants")
    public ResponseEntity<List<ApplicantDto>> getApplicants(@PathVariable Integer jobId) {
        return ResponseEntity.ok(jobService.getApplicants(jobId));
    }
}
