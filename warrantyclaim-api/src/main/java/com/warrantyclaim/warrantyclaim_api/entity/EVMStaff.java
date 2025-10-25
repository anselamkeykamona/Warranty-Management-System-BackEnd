package com.warrantyclaim.warrantyclaim_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "EVM_Staff")
public class EVMStaff {
    @Id
    @Column(name = "EVM_Staff_ID", length = 50)
    private String id;

    @Column(length = 100)
    private String name;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 20, unique = true)
    private String phoneNumber;

    @Column(length = 100)
    private String password;

    @Column(length = 100)
    private String department;

    private LocalDate dateOfBirth;
}
