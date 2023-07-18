package com.lagee.relation.school.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "instructor") //mappedby는 반대쪽 어노테이션이 붙은 필드의 값이다.(테이터 분리 확인을 위해서)
    private List<Lecture> lectures;
}