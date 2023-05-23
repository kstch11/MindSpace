package com.example.mindspace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client extends User {

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name="therapist_id")
    private Therapist therapist;

}
