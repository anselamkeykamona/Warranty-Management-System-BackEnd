package com.warrantyclaim.warrantyclaim_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsSparePartsSCCreateDTO {

    @NotBlank(message = "ID Product Serial SC is required")
    private String id;

    @NotBlank(message = "Product name is required")
    private String name;

    @NotNull(message = "Year of manufacture is required")
    private LocalDate yearOfManufacture;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be zero or positive")
    private Float price;

    @Positive(message = "Warranty period must be positive")
    private Integer warrantyPeriod;

    private String description;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Part Type ID is required")
    private String partTypeId;

    private String claimId;
}
