package com.example.mindspace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "therapists")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Therapist extends User {

    @OneToMany(mappedBy = "therapist")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "therapist")
    private List<Client> clients;

    @ManyToMany
    @JoinTable(
            name = "therapist_theme",
            joinColumns = @JoinColumn(name = "therapist_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id")
    )
    private List<Theme> themes;

    @OneToOne(mappedBy = "therapist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Schedule schedule;

    private boolean approved;
}
