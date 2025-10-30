package com.career.jobs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name = "job_candidate", columnNames = {"jobId", "candidateId"}))
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidates candidate;
    @CreationTimestamp //used to store the present date when record is created, it does not get deleted or updated.
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
