package com.example.mindspace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
public class Admin extends User {
}
