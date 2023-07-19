package com.lagee.relation.school.repository;

import com.lagee.relation.school.entity.Lecture;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.lagee.relation.school.entity.QLecture.lecture;

@RequiredArgsConstructor
public class LectureRepositoryCustomImpl implements LectureRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Lecture> lectureByTime(String day, Integer startTime, Integer endTime) {
        return queryFactory
                .selectFrom(lecture)
                .where(lecture.day.eq(day))
                .fetch();
    }
}