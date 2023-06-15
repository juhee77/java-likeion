package com.lahee.jpa.repository;

import com.lahee.jpa.domain.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    //select * from students order by age
    List<StudentEntity> findAllByOrderByAgeDesc();

    //select * from students order by age desc
    List<StudentEntity> findAllByOrderByAge();

    //select * from students where age<=30
    List<StudentEntity> findAllByAgeLessThan(int age);

    //select * from students where phone like '010-%'
    List<StudentEntity> findAllByPhoneStartingWith(String regx);

}
