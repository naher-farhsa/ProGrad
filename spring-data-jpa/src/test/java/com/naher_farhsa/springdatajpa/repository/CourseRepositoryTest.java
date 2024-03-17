package com.naher_farhsa.springdatajpa.repository;

import com.naher_farhsa.springdatajpa.entity.Course;
import com.naher_farhsa.springdatajpa.entity.Student;
import com.naher_farhsa.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
   private  CourseRepository  courseRepository;

   @Test
   public void printCourses(){
       List<Course> courses=courseRepository.findAll();
       System.out.println("Courses : "+courses);
   }

   @Test
   public void saveCourseWithTeacher(){
       Teacher teacher=Teacher.builder()
               .firstName("Good")
               .lastName("Teacher")
               .build();

       Course course= Course.builder()
               .title("COA")
               .credit(2)
             //  .courseMaterial(courseMaterial)
               .teacher(teacher)
               .build();
       courseRepository.save(course);
   }


   // Paging ->To retrieve data in chunks/pages when there is huge data/record in the table/entity, by specifying the page number and the size of each page.
   // Sorting->To order the retrieved data based on one or more properties/conditions for readability and accessibility.
   @Test
   public void findAllPagination() {

       Pageable firstPageWithTwoRecords = PageRequest.of(1, 3);
       Pageable findPageWithThreeRecords = PageRequest.of(0, 3);
       // pageNumber-> Which page of result pages to be retrieved.
       // pageSize-> Total number of records/row in each page.

       List<Course> courses = courseRepository.findAll(findPageWithThreeRecords).getContent();
       long totalElements = courseRepository.findAll(findPageWithThreeRecords).getTotalElements();
       long totalPages = courseRepository.findAll(findPageWithThreeRecords).getTotalPages();

       System.out.println("Total Elements : "+ totalElements);
       System.out.println("Total Pages : "+ totalPages);
       System.out.println("Courses : "+ courses);

     }

     @Test
     public void findAllSorting(){
         Pageable sortByTitle = PageRequest.of(0,2,Sort.by("title"));

         Pageable sortByCreditDescending=PageRequest.of(0, 2,Sort.by("credit").descending());

         Pageable sortByTitleAndCreditDescending=PageRequest.of(0, 2,Sort.by("title").descending().and(Sort.by("credit")));

         List<Course> courses=courseRepository.findAll(sortByTitleAndCreditDescending).getContent();

         System.out.println("Courses : "+courses);

     }

     @Test
     public void printAllByTitleContaining(){

         Pageable firstPageTenRecords= PageRequest.of(0,10);

         List<Course> courses=courseRepository.findByTitleContaining("C",firstPageTenRecords).getContent();

         System.out.println("Courses : "+courses);
     }

     @Transactional
     @Test
     public void saveCourseWithStudentAndTeacher(){
         Teacher teacher=Teacher.builder()
                 .firstName("Good")
                 .lastName("Bye")
                 .build();
         Student student= Student.builder()
                 .firstName("Good")
                 .lastName("Day")
                 .emailID("day@gmail.com")
                 .build();
         Course course= Course.builder()
                 .title("AIML")
                 .credit(4)
                 //  .courseMaterial(courseMaterial)
                 .teacher(teacher)
                 .build();

         course.addStudents(student);
         courseRepository.save(course);
     }


}