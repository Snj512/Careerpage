package com.career.jobs.dto;

import lombok.Data;

@Data
public class CandidateProfileDto {
    private String fullName;
    private String emailId;
    private int experience;
    private String skills;
    private String locations;
}
