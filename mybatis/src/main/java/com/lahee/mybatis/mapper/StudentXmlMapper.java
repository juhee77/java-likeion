package com.lahee.mybatis.mapper;

import com.lahee.mybatis.model.Student;

import java.util.List;

public interface StudentXmlMapper {
    List<Student> selectStudentAll();

    void deleteStudent(Long id);

    void insertStudent(Student student);

    Student selectStudent(Long id);
}

/*
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.lahee.mybatis.mapper.StudentXmlMapper">
    <select id="selectStudentAll" resultType="Student">
        SELECT * FROM students;
    </select>
</mapper>

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.lahee.mybatis.mapper.StudentMapperX">
    <!-- create -->
    <insert id="insertStudent" parameterType="Student">
        INSERT INTO students(name, age, phone, email)
        VALUES (#{name}, #{age}, #{phone}, #{email})
    </insert>
    <!-- read -->
    <select id="selectStudentAll" resultType="Student">
        SELECT * FROM students;
    </select>
    <select id="selectStudent" parameterType="Long" resultType="Student">
        SELECT * FROM students WHERE id = #{id}
    </select>
    <!-- update -->
    <update id="updateStudent" parameterType="Student">
        UPDATE students
        SET name = #{name},
            age = #{age},
            phone = #{phone},
            email = #{email}
        WHERE id = #{id}
    </update>
    <!-- delete -->
    <delete id="deleteStudent" parameterType="Long">
        DELETE FROM students
        WHERE id = #{id}
    </delete>
</mapper>
*/
