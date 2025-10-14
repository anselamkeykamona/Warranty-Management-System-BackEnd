package com.warrantyclaim.warrantyclaim_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Electric_Vehicle_Type")
public class ElectricVehicleType {
    @Id
    @Column(name = "ID_Electric_Vehicle_Type", length = 50)
    private String id;

    @Column(length = 100)
    private String description;

    @Column(name = "Model_Name", length = 100)
    private String modelName;

    private Integer yearModelYear;

    @Column(name = "Battery_Type", length = 100)
    private String batteryType;

    private Float price;
    private Integer quantity;
}
