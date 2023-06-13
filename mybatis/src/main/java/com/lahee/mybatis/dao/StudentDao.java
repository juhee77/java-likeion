package com.lahee.mybatis.dao;

import com.lahee.mybatis.mapper.StudentMapper;
import com.lahee.mybatis.mapper.StudentXmlMapper;
import com.lahee.mybatis.model.Student;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDao {
    private final SqlSessionFactory sessionFactory;

    public StudentDao(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Student> readStudentsAll() {
        try (SqlSession session = sessionFactory.openSession()) {
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);
            return studentMapper.selectStudentAll();
        }
    }

    public void creatStudent(Student student) {
        try (SqlSession session = sessionFactory.openSession()) {
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);
            studentMapper.insertStudent(student);
        }
    }

    public Student readStudent(Long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);
            return studentMapper.selectStudent(id);
        }
    }

    public void deleteStudent(Long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);
            studentMapper.deleteStudent(id);
        }
    }

}