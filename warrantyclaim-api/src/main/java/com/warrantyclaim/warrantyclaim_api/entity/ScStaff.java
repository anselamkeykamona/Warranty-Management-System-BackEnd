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
@Table(name = "SC_Staff")
public class ScStaff {

    @Id
    @Column(name = "SC_StaffID")
    private String scStaffId;

    @Column(name = "Account_Name")
    private String accountName;

    @Column(name = "Age")
    private Integer age;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Column(name = "Date_of_Birth")
    private LocalDate dateOfBirth;

    @Column(name = "Password")
    private String password;

    @OneToMany(mappedBy = "scStaff")
    private List<PartsRequest> partsRequests = new ArrayList<>();

    @OneToMany(mappedBy = "scStaff")
    private List<WarrantyClaim> warrantyClaims = new ArrayList<>();

    @OneToMany(mappedBy = "scStaff")
    private List<Report> reports = new ArrayList<>();
}