package com.naher_farhsa.springdatajpa.repository;

import com.naher_farhsa.springdatajpa.entity.Course;
import com.naher_farhsa.springdatajpa.entity.CourseMaterial;
import com.naher_farhsa.springdatajpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository repository;

    @Test
    public void saveCourseMaterial(){

        Course course=Course.builder()
                .credit(3)
                .title("DSA")
                .build();
        CourseMaterial courseMaterial= CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();
        repository.save(courseMaterial);
        /* (object references an unsaved transient instance - save the transient instance before flushing)
           This error came, since we are trying to save course material in (Course Material), before saving
           any course in (Course) table. CONVENTIONALLY, WITHOUT COURSE THERE SHOULDN'T BE ANY COURSE MATERIAL.
        */
    }
    @Test
    public void printAllCourseMaterials(){
        List<CourseMaterial> courseMaterialList= repository.findAll();
        System.out.println("Course Material List : "+courseMaterialList);
    }





}