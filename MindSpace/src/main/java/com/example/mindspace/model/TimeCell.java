package com.example.mindspace.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "time_cells")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TimeCell extends AbstractEntity {

    private LocalDateTime startTime;
    private Long duration;

    @OneToOne
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public boolean isReserved() {
        return reservation != null;
    }

    public boolean isExpired() {
        return startTime.isBefore(LocalDateTime.now());
    }
}
