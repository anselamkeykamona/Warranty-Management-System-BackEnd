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
    @Column(name = "RequestID")
    private String requestId;

    @Column(name = "PartNumber")
    private String partNumber;

    @Column(name = "PartName")
    private String partName;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "RequestDate")
    private LocalDate requestDate;

    @Column(name = "Status")
    private String status;

    @Column(name = "DeliveryDate")
    private LocalDate deliveryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Products_Part_Type_SC")
    private ProductsSparePartsTypeSC productsSparePartsTypeSC;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SC_StaffID")
    private ScStaff scStaff;
}