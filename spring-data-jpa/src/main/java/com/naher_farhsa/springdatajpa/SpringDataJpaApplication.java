package com.naher_farhsa.springdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

}
/* To test any entity service or repository create its test class using " alt + insert "
   eg:  Class -> Course
        Repository -> CourseRepository
        Test Class -> CourseRepositoryTest

 */