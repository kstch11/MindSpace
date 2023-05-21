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
public class Reservation extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(mappedBy = "reservation")
    private TimeCell timeCell;

    @ManyToOne
    @JoinColumn(name="therapist_id")
    private Therapist therapist;
}