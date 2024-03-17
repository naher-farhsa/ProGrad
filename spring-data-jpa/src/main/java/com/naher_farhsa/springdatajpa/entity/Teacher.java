package com.naher_farhsa.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {

    @Id
    @SequenceGenerator(
            name= "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
              strategy = GenerationType.SEQUENCE,
              generator = "teacher_sequence"
    )
    private Long teacherID;
    private String firstName;
    private String lastName;

    /*
    @OneToMany( //  Here One-> Teacher(Source Entity) and Many-> Course(Target Entity)
         cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="teacher_ID",
            referencedColumnName = "teacherID"
            //Here teacher_ID will be FK to course table
    )
    // Since one teacher can teach many courses therefore one teacher entity will have a list course entity
    private List<Course> courses;

    According best jpa practices ManyToOne relationship is generally preferred over oneToMany.
    */
}
