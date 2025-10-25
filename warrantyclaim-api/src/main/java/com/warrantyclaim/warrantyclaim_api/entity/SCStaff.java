package com.warrantyclaim.warrantyclaim_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "SC_Staff")
public class SCStaff {
    @Id
    @Column(name = "SC_StaffID", length = 50)
    private String id;

    @Column(name = "Account_Name", length = 100)
    private String accountName;

    private Integer age;

    @Column(length = 100, unique = true)
    private String email;

    @Column(name = "Phone_Number", length = 20, unique = true)
    private String phoneNumber;

    @Column(name = "Date_of_Birth")
    private LocalDate dateOfBirth;

    @Column(name = "BranchOffice", length = 150)
    private String branchOffice;

    @Column(length = 100)
    private String password;
}
