package com.naher_farhsa.springdatajpa.repository;

import com.naher_farhsa.springdatajpa.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {

}
