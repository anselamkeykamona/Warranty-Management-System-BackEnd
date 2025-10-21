package com.warrantyclaim.warrantyclaim_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsSparePartsEVMResponseDTO {

    private String id;
    private String name;
    private LocalDate yearOfManufacture;
    private String brand;
    private Float price;
    private Integer warrantyPeriod;
    private String description;
    private PartTypeInfoDTO partType;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PartTypeInfoDTO {
        private String id;
        private String partName;
        private String description;
        private String manufacturer;
    }
}
