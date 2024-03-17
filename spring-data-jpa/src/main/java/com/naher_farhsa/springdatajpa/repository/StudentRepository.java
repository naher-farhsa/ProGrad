package com.naher_farhsa.springdatajpa.repository;

import com.naher_farhsa.springdatajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    public  List<Student> findByFirstName(String firstName);
    public  List<Student> findByFirstNameContaining(String firstName);
    public  List<Student> findByLastNameNotNull();
    public  List<Student> findByGuardianName(String guardianName);

    @Query(
            "select s from Student s where s.emailID = ?1"
    )   // JPQL Query
    public Student getByEmailAddress(String emailID);

   /*
    @Query("select s.firstName from Student s where s.emailID = ?1")   // JPQL Query
    public String getByEmailAddress(String emailID);
    Here in above query, only firstName(s.firstName) is being retrieved ,so we used String to store with compatible datatype of firstName
    But,if u want to get all Student details we use Student object to retrieve it.
    */

   /*
   JPQL queries are different from Native queries
   JPQL-> @Query("select u from User u where u.firstname like %?1")
   Native->@Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
   */

    @Query(
            value = "SELECT * FROM schooldb.tbl_student where email_address= ?1", // Here ?1-> first parameter likewise we can give ?2, ?3, etc
            nativeQuery = true
    )   // Native SQl Query
    public Student getByEmailAddressNative(String emailID);

    @Query(
            value = "SELECT * FROM schooldb.tbl_student where email_address=:emailID", //Here instead of giving email_address=?1 we can give email_address=:emailID
            nativeQuery = true                                                         // As for much more understandable with Query named parameter
    )   // Query Named Params
    public Student getByEmailAddressNativeNamedParam(@Param("emailID") String emailID);

    // Up till now we only make jpa methods for to fetch data .
    // Below we will see how to update/delete/modify, etc data

    @Modifying  // Since, this query will update/modify the row in DB, so we should annotate it with @Modifying
    @Transactional // To commit back the updated/modified data to ensure that it is completed successfully
    @Query( value ="UPDATE schooldb.tbl_student set first_name=?1 where (studentId=?2 and email_address=?3)",
            nativeQuery = true
            // keep schooldb.tbl_student not tbl_student in the query
    )
    public void updateStudentNameByEmailIDAndStudentID(String firstName,Long studentID,String emailID);

    @Modifying  // Since, this query will update/modify the row in DB, so we should annotate it with @Modifying
    @Transactional // To commit back the updated/modified data to ensure that it is completed successfully
    @Query( value ="UPDATE schooldb.tbl_student set guardian_mobile=?1 where (studentId=?2)",
            nativeQuery = true
            // keep schooldb.tbl_student not tbl_student in the query
    )
    public void updateGuardianMobileByStudentID(String mobile ,Long studentID);

}
