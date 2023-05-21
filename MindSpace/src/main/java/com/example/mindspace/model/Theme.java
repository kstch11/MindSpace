package com.example.mindspace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "themes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Theme extends AbstractEntity {
    private String name;

    @ManyToMany(mappedBy = "themes")
    private List<Therapist> therapists;


}
