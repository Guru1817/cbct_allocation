package com.guru.cbct.cbct_allocation.subject;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder   // 🔹 this generates Subject.builder()
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int totalSeats;
    private int availableSeats;
}
