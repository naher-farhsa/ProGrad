package com.naher_farhsa.springdatajpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
   @Id  // To mark PK of table
   @SequenceGenerator(     // Defines how the values of PK is generated in sequence
           name = "course_sequence",        // Name of sequence generator
           sequenceName = "course_sequence", // Specify sequence name in database as a table
           allocationSize = 1
   )
   @GeneratedValue(
           strategy = GenerationType.SEQUENCE, // Here generation type is - sequence
           generator = "course_sequence"  // "name=course_sequence" refer to name in sequence generator
   )
    private long  courseID;
    private String title;
    private Integer credit;
    /* ⬆️⬆️ Up till now, there is unidirectional one2one mapping between course and course material ⬆️⬆️ */

    /* ⬇️⬇️ Below we are creating a bidirectional one2one mapping between these entities ⬇️⬇️*/
    @OneToOne(
            /* Here One-> Course(Source Entity) and One-> CourseMaterial(Target Entity) */
            mappedBy = "course"
    )
    private CourseMaterial courseMaterial;

    @ManyToOne(  // Note: This is still unidirectional ManyToOne relationship
                /* Here Many-> Course(Source Entity) and One->Teacher(Target Entity) */

            cascade = CascadeType.DETACH
    )
    @JoinColumn(
            name="teacher_id",
            referencedColumnName = "teacherID"
    )
    private Teacher teacher;

    @ManyToMany ( /* Here Many-> Course(Source Entity) and Many-> Student(Target Entity) */
            cascade = CascadeType.ALL
    )
    @JoinTable(  /*Each record/row in one entity can be associated with multiple records in another entity,& vice versa.
                 Therefore,@JoinTable is used here.Basically we are creating a third table(Join Table) using source and target table */

            name="student_course_map",  // Join table->student_course_map

            joinColumns =@JoinColumn(  //This is given to Source Entity(Course)
                    name = "course_id",
                    referencedColumnName = "courseID"
            ),
            inverseJoinColumns = @JoinColumn(  // This is given to Target Entity(Student)
                    //inverseJoinColumns->To specify the FK column(s) in the join table that reference the primary key column(s) of the inverse side of the relationship.
                    name="student_id",
                    referencedColumnName = "studentID"
            )
    )
    private List<Student> students;

    public void addStudents(Student student){
        if(students==null)
          students= new ArrayList<>();
        else
            students.add(student);
    }
}
 //@JoinTable is used to define the mapping for a many-to-many relationship between two entities.
 //@JoinColumn is used to define the mapping for a foreign key column in an entity that references another entity.