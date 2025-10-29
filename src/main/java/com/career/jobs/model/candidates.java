package com.career.jobs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class candidates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidateId", nullable = false)
    private Integer id;
    private String FullName;
    private String emailId;
    private String password;
    private int experience;
    private String skills;
    private String locations;

}
