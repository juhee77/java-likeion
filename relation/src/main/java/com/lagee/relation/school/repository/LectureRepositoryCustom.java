package com.lagee.relation.school.repository;

import com.lagee.relation.school.entity.Instructor;
import com.lagee.relation.school.entity.Lecture;

import java.util.List;

public interface LectureRepositoryCustom {
    List<Lecture> lectureByTime(String day, Integer startTime, Integer endTime);
}