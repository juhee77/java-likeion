package com.lagee.relation.school.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToMany(cascade = CascadeType.ALL) //상위 엔터티에서 하위 엔터티로 모든 작업을 전파
    @JoinTable(name = "student-attending-table") //join 테이블의 이름을 지정한다.
    private List<Lecture> attending;
}
