package com.naher_farhsa.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;   // To create instance of class using builder pattern
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity   // To map with database
@Data    //  Automatically generates standard boilerplate code for a Java class, including getters, setters, toString(), equals(), and hashCode() methods.
@AllArgsConstructor
@NoArgsConstructor
@Builder  //This "@Builder" is used to eliminate creating builder object for class manually, it will work by springboot.
// The above 4  are lombok annotation which helps to create a pojo class here i.e. auto generated constructor and getter-setter
@Table(name = "tbl_Student")   // new table wll be
public class Student {

    @Id  // To mark PK (Primary Key) in the table
    @SequenceGenerator(     // Defines how the values of PK is generated in sequence
            name = "student_sequence",        // Name of sequence generator
            sequenceName = "student_sequence", // Specify sequence name in database as a table
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, // Here generation type is - sequence
            generator = "student_sequence"  // "name=student_sequence" refer to name in sequence generator
    )
    private Long studentID;
    private String firstName;
    private String lastName;
    @Column(
            name = "email_address",
            nullable = false         // "emailID" column must not be null/empty
    )
    private String emailID;

    @Embedded //When a separate guardian-table(Entity class) in db isn't required but, its values need to be embedded into other entity class (Student).
    private Guardian guardian;

}
