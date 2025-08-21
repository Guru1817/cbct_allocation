package com.guru.cbct.cbct_allocation.student;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data   // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String rollNo;

    private String option1;
    private String option2;
    private String option3;

    private String allocatedSubject; // final assigned subject
}
