package com.lagee.relation.school.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
//    @JoinColumn(name = "instructor") //조인 컬럼의 이름을 지정해준다.(이쪽이 종속된는것이다)
    private Instructor instructor;

    @ManyToMany(mappedBy = "attending")
    // 이쪽이 종속되는 컬럼이라는 것을 알린다
    private List<Student> students;
}