package org.javaguru.travel.insurance.core.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "classifiers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classifier {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;
}
