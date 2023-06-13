package com.lahee.mybatis.mapper;

import com.lahee.mybatis.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface StudentMapper {
    @Select("SELECT * FROM students")
    List<Student> selectStudentAll();

    @Select("SELECT * FROM students WHERE id = #{id}")
    Student selectStudent(Long id);

    @Insert("INSERT INTO students(name, age, phone, email) VALUES(#{name}, #{age}, #{phone}, #{email})")
    void insertStudent(Student student);

    @Update("UPDATE students SET " +
            "name = #{name}," +
            "age = #{age}," +
            "phone = #{phone}," +
            "email = #{email}" +
            "WHERE id = #{id}")
    void updateStudent(Student student);

    @Delete("DELETE FROM students " +
            "WHERE id = #{id}")
    void deleteStudent(Long id);

    @Select("SELECT * FROM students WHERE id = #{id}")
    Optional<Student> selectStudentOptional(Long id);
}