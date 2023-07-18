package com.lagee.relation.school.repository;

import com.lagee.relation.school.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
