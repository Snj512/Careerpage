package com.career.jobs.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCandidateDto {
    private String FullName;
    private String emailId;
    private String password;
    private Integer experience;
    private String skills;
    private String locations;

}
