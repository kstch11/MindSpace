package com.example.mindspace.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "time_cells")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TimeCell extends AbstractEntity {
    private LocalDateTime time;
    private boolean reserved = false;
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

}
