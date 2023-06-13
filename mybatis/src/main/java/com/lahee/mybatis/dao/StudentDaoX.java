package com.lahee.mybatis.dao;

import com.lahee.mybatis.mapper.StudentXmlMapper;
import com.lahee.mybatis.model.Student;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoX {
    private final SqlSessionFactory sessionFactory;

    public StudentDaoX(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Student> readAllXmlStudent() {
        try (SqlSession session = sessionFactory.openSession()) {
            StudentXmlMapper studentXmlMapper = session.getMapper(StudentXmlMapper.class);
            return studentXmlMapper.selectStudentAll();
        }
    }

    public Student readStudentXml(Long id) {
        try (SqlSession session = sessionFactory.openSession()){
            StudentXmlMapper studentXmlMapper = session.getMapper(StudentXmlMapper.class);
            return studentXmlMapper.selectStudent(id);
        }
    }
}