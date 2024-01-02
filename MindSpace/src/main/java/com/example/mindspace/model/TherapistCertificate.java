package com.example.mindspace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "certificates")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TherapistCertificate extends AbstractEntity {

    @Column
    private String fileUrl;

    @ManyToOne
    @JoinColumn(name="therapist_id")
    private Therapist therapist;
}
