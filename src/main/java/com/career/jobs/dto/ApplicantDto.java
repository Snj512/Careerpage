package com.career.jobs.dto;

import lombok.Data;

@Data
public class ApplicantDto {
    private String fullName;
    private String email;
    private int experience;
    private String skills;
    private String appliedAt;
}
