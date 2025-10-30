package com.career.jobs.service;

import com.career.jobs.dto.*;
import com.career.jobs.model.*;
import com.career.jobs.model.Candidates;
import com.career.jobs.model.Job;
import com.career.jobs.model.JobApplication;
import com.career.jobs.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateJobService {

    private final JobRepository jobRepository;
    private final CandidateRepository candidateRepository;
    private final JobApplicationRepository jobApplicationRepository;

    //Public: List all jobs (minimal info)
    public List<PublicJobDto> getAllJobs() {
        return jobRepository.findAll().stream().map(job -> {
            PublicJobDto dto = new PublicJobDto();
            dto.setId(job.getId());
            dto.setTitle(job.getTitle());
            dto.setExperienceReq(job.getExperienceReq());
            dto.setLocation(job.getLocation());
            dto.setCreatedAt(job.getCreatedAt());
            return dto;
        }).collect(Collectors.toList());
    }

    //Public: View specific job details
    public JobDetailDto getJobDetails(Integer jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        JobDetailDto dto = new JobDetailDto();
        dto.setId(job.getId());
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setSkillRequired(job.getSkillRequired());
        dto.setExperienceReq(job.getExperienceReq());
        dto.setLocation(job.getLocation());
        dto.setPostedDate(job.getCreatedAt()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        return dto;
    }

    // Candidate: Apply for a job
    public ApiResponse applyForJob(Integer jobId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Candidates candidate = candidateRepository.findByEmailId(email)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        boolean alreadyApplied = jobApplicationRepository.existsByJobAndCandidate(job, candidate);
        if (alreadyApplied) {
            return new ApiResponse("error", "You have already applied for this job");
        }

        JobApplication application = new JobApplication();
        application.setJob(job);
        application.setCandidate(candidate);
        jobApplicationRepository.save(application);

        return new ApiResponse("success", "Applied successfully for job: " + job.getTitle());
    }

    //Candidate: Get all jobs applied by candidate
    public List<JobDetailDto> getAppliedJobs() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Candidates candidate = candidateRepository.findByEmailId(email)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        return jobApplicationRepository.findByCandidate(candidate)
                .stream().map(app -> {
                    Job job = app.getJob();
                    JobDetailDto dto = new JobDetailDto();
                    dto.setId(job.getId());
                    dto.setTitle(job.getTitle());
                    dto.setDescription(job.getDescription());
                    dto.setSkillRequired(job.getSkillRequired());
                    dto.setExperienceReq(job.getExperienceReq());
                    dto.setLocation(job.getLocation());
                    dto.setPostedDate(job.getCreatedAt()
                            .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                    return dto;
                }).collect(Collectors.toList());
    }
}
