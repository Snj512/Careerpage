package com.career.jobs.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRecruiterDto {
    private String FullName;
    private String company;
    private String emailId;
    private String password;
    private Long createdBy; // optional: id of admin recruiter creating this recruiter
    private String role; //admin or recruiter
}
