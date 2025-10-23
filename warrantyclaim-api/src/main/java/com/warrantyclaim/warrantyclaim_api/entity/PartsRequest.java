package com.warrantyclaim.warrantyclaim_api.entity;

import com.warrantyclaim.warrantyclaim_api.enums.PartsRequestStatus;
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

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "RequestDate")
    private LocalDate requestDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private PartsRequestStatus status;

    @Column(name = "DeliveryDate")
    private LocalDate deliveryDate;

    @ManyToOne  
    @JoinColumn(name = "ID_Products_Part_Type_SC")
    private ProductsSparePartsTypeSC partType;

    @ManyToOne
    @JoinColumn(name = "Vehicle_VIN_ID")
    private ElectricVehicle electricVehicle;

    @ManyToOne
    @JoinColumn(name = "SC_StaffID")
    private SCStaff staff;
}
