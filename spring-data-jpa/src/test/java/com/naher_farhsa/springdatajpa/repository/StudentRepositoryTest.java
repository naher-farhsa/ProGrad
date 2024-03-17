package com.naher_farhsa.springdatajpa.repository;

import com.naher_farhsa.springdatajpa.entity.Guardian;
import com.naher_farhsa.springdatajpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest  // To include in springboot-data-jpa project for testing
class StudentRepositoryTest {
    @Autowired  // Auto dependencies injection
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student= Student.builder()     // Here student object is created using builder pattern.
                .firstName("hi")
                .lastName("world")
                .emailID("world@gmail.com")
              //.guardianName("hello universe")
              //.guardianEmail("universe@gmail.com")
              //.guardianMobile("0000000000")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian=Guardian.builder()
                .name("hello galaxy")
                .email("galaxy@gmail.com")
                .mobile("xxxxxxxxxx")
                .build();

        Student student= Student.builder()
                .firstName("hello")
                .lastName("solar")
                .emailID("solar@gmail.com")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }
    @Test
    public void printAllStudent(){
        List<Student> studentList= studentRepository.findAll();
        System.out.println("Student List : "+studentList);
    }
    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> studentList= studentRepository.findByFirstNameContaining("hi");
        System.out.println("Student List : "+studentList);
    }
    @Test
    public void printStudentByLastNameNotNull(){
        List<Student> studentList= studentRepository.findByLastNameNotNull();
        System.out.println("Student List : "+studentList);
    }
    @Test
    public void printStudentByGuardianName(){
        List<Student> studentList= studentRepository.findByGuardianName("hello galaxy");
        System.out.println("Student List : "+studentList);
    }
    @Test
    public void printStudentByEmailId(){
        Student student= studentRepository.getByEmailAddress("solar@gmail.com");
        //String student=studentRepository.getByEmailAddress("solar@gmail.com");
        System.out.println("Student : "+student);
    }
    @Test
    public void printStudentByEmailIdNative(){
        Student student= studentRepository.getByEmailAddressNative("solar@gmail.com");
        //String student=studentRepository.getByEmailAddress("solar@gmail.com");
        System.out.println("Student : "+student);
    }
    @Test
    public void printStudentByEmailIdNativeNamedParam(){
        Student student= studentRepository.getByEmailAddressNativeNamedParam("solar@gmail.com");
        //String student=studentRepository.getByEmailAddress("solar@gmail.com");
        System.out.println("Student : "+student);
    }
    @Test
    public void updateStudentNameByEmailAndStudentID(){
         studentRepository.updateStudentNameByEmailIDAndStudentID("hey",3L,"earth@gmail.com");
        //String student=studentRepository.getByEmailAddress("solar@gmail.com");
        System.out.println("First name, Updated Successful!");
    }
    @Test
    public void updateGuardianMobileByStudentID(){
         studentRepository.updateGuardianMobileByStudentID("9922114455",2L);
        System.out.println("Guardian Mobile, Updated Successful!");
    }

}
