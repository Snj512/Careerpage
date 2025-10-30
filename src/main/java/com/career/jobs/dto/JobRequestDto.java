package com.career.jobs.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobRequestDto {
    private String title;
    private String description;
    private String skillRequired;
    private int experienceReq;
    private String location;
}

