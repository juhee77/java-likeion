package com.lagee.relation.school.repository;

import com.lagee.relation.school.entity.Instructor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.lagee.relation.school.entity.QInstructor.instructor;

@Repository
@Slf4j
//querydsl support는 필수는 아니다. 여러 관련 메서드를 사용하기 위해 추가한것이다.
public class InstructorRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public InstructorRepositorySupport(JPAQueryFactory queryFactory) {
        super(Instructor.class);
        this.queryFactory = queryFactory;
    }


    // first_name을 기준으로 조회하는 Querydsl식 조회
    public List<Instructor> findByFirstName(String name) {
        /*
        SELECT * FROM instructor WHERE first_name = name
         */
        return queryFactory
                .selectFrom(instructor)
                // 어떤 테이블의 레코드의 어떤 컬럼이 무엇과 일치하는지
                .where(instructor.firstName.eq(name))
                .fetch();
    }

    public List<Instructor> findByFirstNameOrLastName(String name) {
        /*
        SELECT * FROM instructor WHERE first_name = name
         */
        return queryFactory
                .selectFrom(instructor)
                // 어떤 테이블의 레코드의 어떤 컬럼이 무엇과 일치하는지
                .where(instructor.firstName.eq(name)
                        .or(instructor.lastName.eq(name)))
                .fetch();
    }
}