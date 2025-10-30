package com.career.jobs.service;

import com.career.jobs.dto.ApplicantDto;
import com.career.jobs.dto.JobRequestDto;
import com.career.jobs.dto.JobResponseDto;
import com.career.jobs.model.Candidates;
import com.career.jobs.model.Job;
import com.career.jobs.model.Recruiter;
import com.career.jobs.repository.JobApplicationRepository;
import com.career.jobs.repository.JobRepository;
import com.career.jobs.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final RecruiterRepository recruiterRepository;
    private final JobApplicationRepository jobApplicationRepository;

    // Helper to get logged-in recruiter
    private Recruiter getLoggedInRecruiter() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return recruiterRepository.findByEmailId(email)
                .orElseThrow(() -> new RuntimeException("Recruiter not found for logged-in user"));
    }

    //1. Create Job
    public JobResponseDto createJob(JobRequestDto dto) {
        Recruiter recruiter = getLoggedInRecruiter();

        Job job = new Job();
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setSkillRequired(dto.getSkillRequired());
        job.setExperienceReq(dto.getExperienceReq());
        job.setLocation(dto.getLocation());
        job.setPostedBy(recruiter);

        Job saved = jobRepository.save(job);

        JobResponseDto res = new JobResponseDto();
        res.setId(saved.getId());
        res.setTitle(saved.getTitle());
        res.setDescription(saved.getDescription());
        res.setSkillRequired(saved.getSkillRequired());
        res.setExperienceReq(saved.getExperienceReq());
        res.setLocation(saved.getLocation());
        res.setRecruiterName(recruiter.getFullName());
        res.setCompany(recruiter.getCompany());
        res.setCreatedAt(saved.getCreatedAt());

        return res;
    }

    //2. View all jobs by logged-in recruiter
    public List<JobResponseDto> getMyJobs() {
        Recruiter recruiter = getLoggedInRecruiter();
        return jobRepository.findByPostedBy(recruiter).stream().map(job -> {
            JobResponseDto res = new JobResponseDto();
            res.setId(job.getId());
            res.setTitle(job.getTitle());
            res.setDescription(job.getDescription());
            res.setSkillRequired(job.getSkillRequired());
            res.setExperienceReq(job.getExperienceReq());
            res.setLocation(job.getLocation());
            res.setRecruiterName(recruiter.getFullName());
            res.setCompany(recruiter.getCompany());
            res.setCreatedAt(job.getCreatedAt());
            return res;
        }).collect(Collectors.toList());
    }

    public List<ApplicantDto> getApplicants(Integer jobId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Recruiter recruiter = recruiterRepository.findByEmailId(email)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // ensure recruiter can only view applicants for his/her job
//        if (!job.getPostedBy().getId().equals(recruiter.getId())) {
//            throw new RuntimeException("Access denied: You can only view applicants for your own jobs");
//        }

        return jobApplicationRepository.findByJob(job)
                .stream()
                .map(app -> {
                    Candidates c = app.getCandidate();
                    ApplicantDto dto = new ApplicantDto();
                    dto.setFullName(c.getFullName());
                    dto.setEmail(c.getEmailId());
                    dto.setExperience(c.getExperience());
                    dto.setSkills(c.getSkills());
                    dto.setAppliedAt(app.getCreatedAt()
                            .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                    return dto;
                }).collect(Collectors.toList());
    }
}
