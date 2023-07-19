package com.lagee.relation.school.repository;

import com.lagee.relation.school.entity.Instructor;
import com.lagee.relation.school.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> ,LectureRepositoryCustom {
    List<Lecture> findAllByInstructor(Instructor entity);
    List<Lecture> findAllByInstructorId(Long id);
}
