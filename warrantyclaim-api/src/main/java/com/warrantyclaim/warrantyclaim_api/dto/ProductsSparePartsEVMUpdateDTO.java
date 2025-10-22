package com.warrantyclaim.warrantyclaim_api.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsSparePartsEVMUpdateDTO {

    private String name;
    private LocalDate yearOfManufacture;
    private String brand;

    @PositiveOrZero(message = "Price must be zero or positive")
    private Float price;

    private Integer warrantyPeriod;
    private String description;
    private String partTypeId;
}
