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
public class ProductsSparePartsSCResponseDTO {

    private String id;
    private String name;
    private LocalDate yearOfManufacture;
    private Float price;
    private Integer warrantyPeriod;
    private String description;
    private String brand;
    private PartTypeInfoDTO partType;
    private ClaimInfoDTO claim;

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

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClaimInfoDTO {
        private String claimId;
        private String customerName;
        private String issueDescription;
    }
}
