package com.naher_farhsa.springdatajpa.repository;

import com.naher_farhsa.springdatajpa.entity.Course;
import com.naher_farhsa.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private  TeacherRepository teacherRepository;

    @Test
    public void savaTeacher(){
        Course course1= Course.builder()
                .title("DBMS")
                .credit(3)
                .build();
        Course course2= Course.builder()
                .title("DSA")
                .credit(3)
                .build();
        Course course3= Course.builder()
                .title("WEB DEV")
                .credit(4)
                .build();
        // Ideally a method should be used to add course to the course list

        Teacher teacher=Teacher.builder()
                .firstName("Good")
                .lastName("Morning")
               // .courses(List.of(course1,course2,course3))
                .build();

        teacherRepository.save(teacher);

    }
}