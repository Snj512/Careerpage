package com.career.jobs.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobResponseDto {
    private Integer id;
    private String title;
    private String description;
    private String skillRequired;
    private int experienceReq;
    private String location;
    private String recruiterName;
    private String company;
    private LocalDateTime createdAt;
}