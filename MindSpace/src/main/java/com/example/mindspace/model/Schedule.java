package com.example.mindspace.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "schedules")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Schedule extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @OneToMany(mappedBy = "schedule")
    private List<TimeCell> availableTimeCells;
}
