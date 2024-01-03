package com.example.mindspace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "therapists")
@AllArgsConstructor
@Getter
@Setter
public class Therapist extends User {

    public Therapist() {
        setUserType(UserType.THERAPIST);
    }

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

    private LocalDateTime birthDate;

    @Column(length = 1024)
    private String description;

    private String education;

    @OneToMany(mappedBy = "therapist")
    private List<SpokenLanguage> languages;

    private String personalTherapy;

    private String photo;

    private String therapeuticCommunity;

    @OneToMany(mappedBy = "therapist")
    private List<TherapistCertificate> certificates;

    private boolean approved;
}
