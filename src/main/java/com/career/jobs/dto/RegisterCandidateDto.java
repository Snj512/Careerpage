package com.career.jobs.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCandidateDto {
    private String fullName;
    private String emailId;
    private String password;
    private int experience;
    private String skills;
    private String locations;

}
