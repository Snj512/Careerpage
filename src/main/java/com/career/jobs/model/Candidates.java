package com.career.jobs.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Candidates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidateId", nullable = false)
    private Integer id;
    @Column(length = 40)
    private String FullName;

    @Column(unique = true)
    private String emailId;

    private String password;
    private int experience;
    private String skills;
    private String locations;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobApplication> applications;

}
