package com.naher_farhsa.springdatajpa.repository;

import com.naher_farhsa.springdatajpa.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial,Long> {


}
