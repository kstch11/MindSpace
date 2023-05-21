package com.example.mindspace.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation extends AbstractEntity{

    @ManyToMany
    @JoinTable(
            name = "client_reservation",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> members;

    private LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name="therapist_id")
    private Therapist therapist;
}
