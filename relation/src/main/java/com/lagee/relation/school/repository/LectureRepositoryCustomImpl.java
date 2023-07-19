package com.lagee.relation.school.repository;

import com.lagee.relation.school.entity.Lecture;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.lagee.relation.school.entity.QLecture.lecture;

@RequiredArgsConstructor
public class LectureRepositoryCustomImpl implements LectureRepositoryCustom {
    private final JPAQueryFactory queryFactory;

//    @Override
//    public List<Lecture> lectureByTime(String day, Integer startTime, Integer endTime) {
//        return queryFactory
//                .selectFrom(lecture)
//                .where(lecture.day.eq(day))
//                .fetch();
//    }

//    @Override
//    public List<Lecture> lectureByTime(String day, Integer startTime, Integer endTime) {
//        BooleanBuilder builder = new BooleanBuilder();
//        if (StringUtils.isNullOrEmpty(day)) {
//            builder.and(lecture.day.eq(day));
//        }
//        if (startTime != null) {
//            builder.and(lecture.startTime.eq(startTime));
//        }
//        if (endTime != null) {
//            builder.and(lecture.endTime.eq(endTime));
//        }
//        return queryFactory
//                .selectFrom(lecture)
//                .where(builder)
//                .fetch();
//    }

    @Override
    public List<Lecture> lectureByTime(
            String day,
            Integer startTime,
            Integer endTime
    ) {
        return queryFactory
                .selectFrom(lecture)
                .where(
                        eqDay(day),
                        eqStartTime(startTime),
                        eqEndTime(endTime)
                ).fetch();
    }

    private BooleanExpression eqDay(String day) {
        if (StringUtils.isNullOrEmpty(day)) {
            return null;
        }
        return lecture.day.eq(day);
    }

    private BooleanExpression eqStartTime(Integer startTime) {
        if (startTime == null) {
            return null;
        }
        return lecture.startTime.eq(startTime);
    }

    private BooleanExpression eqEndTime(Integer endTime) {
        if (endTime == null) {
            return null;
        }
        return lecture.startTime.eq(endTime);
    }


}