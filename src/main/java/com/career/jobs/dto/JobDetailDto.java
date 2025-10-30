package com.career.jobs.dto;

import lombok.Data;

@Data
public class JobDetailDto {
    private Integer id;
    private String title;
    private String description;
    private String skillRequired;
    private int experienceReq;
    private String location;
    private String postedDate;
}
