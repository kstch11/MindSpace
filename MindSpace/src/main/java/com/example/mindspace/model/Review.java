package com.example.mindspace.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reviews")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Review extends AbstractEntity {

    private String text;

    @ManyToOne
    private Client author;

    @ManyToOne
    private Therapist recipient;

}
