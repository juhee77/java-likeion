package com.lagee.relation.school.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String day;
    private Integer startTime;
    private Integer endTime;

    @ManyToOne
    private Instructor instructor;
}