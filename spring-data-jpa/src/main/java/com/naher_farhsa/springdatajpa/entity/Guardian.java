package com.naher_farhsa.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable  // To  target class that is to be embedded by springboot.
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverrides( {
        @AttributeOverride(
                name = "name",
                column = @Column(name = "guardian_name")
        ),
       @AttributeOverride(
                name = "email",
                column = @Column(name = "guardian_email")
        ),
        @AttributeOverride(
                name = "mobile",
                column = @Column(name = "guardian_mobile")
        )
   }
)
public class Guardian {
    private String name;
    private String email;
    private String mobile;

}
