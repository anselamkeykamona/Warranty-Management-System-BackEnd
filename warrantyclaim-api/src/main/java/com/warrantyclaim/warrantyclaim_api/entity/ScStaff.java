package com.warrantyclaim.warrantyclaim_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "SC_Staff")
public class ScStaff {
    @Id
    @Column(name = "SC_StaffID")
    private String scStaffId;

    @Column(name = "Account_Name")
    private String accountName;

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
    private List<PartsRequest> partsRequests;

    @OneToMany(mappedBy = "scStaff")
    private List<WarrantyClaim> warrantyClaims;

    @OneToMany(mappedBy = "scStaff")
    private List<Report> reports;
}


