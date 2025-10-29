package com.career.jobs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JobId", nullable = false)
    private Integer id;
    private String Title;
    private String Description;
    private String SkillRequired;
    private int experienceReq;
    private String location;
    private int recruiterId;

}
