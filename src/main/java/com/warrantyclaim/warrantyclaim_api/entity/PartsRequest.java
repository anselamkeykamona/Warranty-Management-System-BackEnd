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
@Table(name = "Parts_Request")
public class PartsRequest {
    @Id
    @Column(name = "RequestID", length = 50)
    private String id;

    @Column(name = "PartNumber", length = 50)
    private String partNumber;

    @Column(name = "PartName", length = 100)
    private String partName;

    private Integer quantity;
    private LocalDate requestDate;
    private String status;
    private LocalDate deliveryDate;

    @ManyToOne
    @JoinColumn(name = "ID_Products_Part_Type_SC")
    private ProductsSparePartsTypeSC partType;

    @ManyToOne
    @JoinColumn(name = "SC_StaffID")
    private SCStaff staff;
}
