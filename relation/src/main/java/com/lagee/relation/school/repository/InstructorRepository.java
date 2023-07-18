package com.lagee.relation.school.repository;

import com.lagee.relation.school.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
