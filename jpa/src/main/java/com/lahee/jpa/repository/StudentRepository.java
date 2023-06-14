package com.lahee.jpa.repository;

import com.lahee.jpa.domain.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
