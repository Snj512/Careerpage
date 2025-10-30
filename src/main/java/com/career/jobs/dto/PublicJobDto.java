package com.career.jobs.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PublicJobDto {
    private Integer id;
    private String title;
    private int experienceReq;
    private String location;
    private LocalDateTime createdAt;
}
