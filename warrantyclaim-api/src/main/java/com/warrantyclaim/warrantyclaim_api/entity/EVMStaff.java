package com.warrantyclaim.warrantyclaim_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "EVM_Staff")
public class EVMStaff {
    @Id
    @Column(name = "EVM_Staff_ID")
    private String evmStaffId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "Password")
    private String password;

    @Column(name = "Department")
    private String department;

    @Column(name = "DateOfBirth")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "evmStaff")
    private List<Report> reports = new ArrayList<>();
}
