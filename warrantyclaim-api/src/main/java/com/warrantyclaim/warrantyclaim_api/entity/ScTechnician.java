package com.warrantyclaim.warrantyclaim_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "SC_Technician")
public class ScTechnician {

    @Id
    @Column(name = "SC_TechnicianID")
    private String scTechnicianId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email", unique = true)
    @Email(message = "Email Invalid format!!!")
    private String email;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Column(name = "Date_of_Birth")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @Column(name = "Password")
    private String password;

    @Column(name = "Specialty")
    private String specialty;

    @OneToMany(mappedBy = "scTechnician")
    private List<WorkAssign> workAssigns = new ArrayList<>();
}