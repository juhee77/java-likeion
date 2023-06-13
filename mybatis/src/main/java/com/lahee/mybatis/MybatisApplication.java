package com.lahee.mybatis;


import com.lahee.mybatis.dao.StudentDao;
import com.lahee.mybatis.dao.StudentDaoX;
import com.lahee.mybatis.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MybatisApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext
                = SpringApplication.run(MybatisApplication.class, args);

        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        StudentDao dao = applicationContext.getBean(StudentDao.class);
        System.out.println(dao.readStudentsAll());

        dao.creatStudent(new Student(5L, "test", 20, "010-1234-5678", "bc@naver.com"));
        System.out.println(dao.readStudent(5L));
        dao.deleteStudent(5L);
        System.out.println("dao " + dao.readStudentsAll());

        StudentDaoX daoX = applicationContext.getBean(StudentDaoX.class);
        System.out.println("daoX " + daoX.readStudentXml(5L));
        System.out.println("daoX" + daoX.readAllXmlStudent());
    }
}
