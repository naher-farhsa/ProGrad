package com.naher_farhsa.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
public class CourseMaterial {
    @Id
    @SequenceGenerator(     // Defines how the values of PK is generated in sequence
            name = "course_material_sequence",        // Name of sequence generator
            sequenceName = "course_material_sequence", // Specify sequence name in database as a table
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, // Here generation type is - sequence
            generator = "course_material_sequence" // "name=course_material_sequence" refer to name in sequence generator
    )
    private long courseMaterialID;
    private String url;

    @OneToOne(          // OneToOne mapping-> here there is only one course material for one course and vice verse
            /* Here One-> CourseMaterial(Source Entity) and One-> Course(Target Entity) */
            cascade = CascadeType.ALL,
            /* cascade-> Actions(persist, merge, remove) performed on one entity automatically trigger corresponding actions on
            related entities, streamlining database operations and maintaining data consistency.It is for every relation mapping

            Here the cascading is given to course material means if there is any change it this table then it will
            be reflected in course table but "NOT VICE VERSA".

            • CascadeType.ALL: Specifies that all operations (persist, merge, remove, refresh) should be cascaded.
            • CascadeType.PERSIST: Specifies that the persist operation should be cascaded.
            • CascadeType.MERGE: Specifies that the merge operation should be cascaded.
            • CascadeType.REMOVE: Specifies that the remove operation should be cascaded.
            • CascadeType.REFRESH: Specifies that the refresh operation should be cascaded.
            • CascadeType.DETACH: Specifies that the detach operation should be cascaded.
            */
            fetch =FetchType.LAZY,
            /* fetch-> Strategy used for retrieving associated entities here (course <-> courseMaterial) when querying the database.
               • FetchType.LAZY: Retrieving of associated entities data  from the DB  are done only when explicitly(specifically) asked to do so.
               • FetchType.EAGER:Retrieving of associated entities data should be done immediately along with the owning entity(Parent Table),
               even if they might not be immediately used.
            */
            optional = false // It means here course material entity is mandatory to define a course entity
                            // i.e, course material is not optional (optional =false) by default it will be true
    )
    @JoinColumn(
            name = "course_Id",
            referencedColumnName = "courseID"
            //Here (courseID) of course table will be FK (foreign key) reference in course material table
    )
    /* Since, Course material table cannot exist standalone,
    there must be a course for each course material */
    private Course course;
}
