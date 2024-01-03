package com.example.mindspace.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public abstract class User extends AbstractEntity {
    private String name;

    private String surname;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public enum UserType {
        CLIENT, THERAPIST, ADMIN;
    }

    private boolean registrationFinished;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public enum Gender {
        MALE, FEMALE, NOT_STATED
    }
}
