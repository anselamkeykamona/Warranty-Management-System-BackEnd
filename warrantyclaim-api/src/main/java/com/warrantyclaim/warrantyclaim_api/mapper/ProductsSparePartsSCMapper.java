package com.warrantyclaim.warrantyclaim_api.mapper;

import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsSCCreateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsSCResponseDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsSCUpdateDTO;
import com.warrantyclaim.warrantyclaim_api.entity.ProductsSparePartsSC;
import com.warrantyclaim.warrantyclaim_api.entity.ProductsSparePartsTypeSC;
import com.warrantyclaim.warrantyclaim_api.entity.WarrantyClaim;
import org.springframework.stereotype.Component;

@Component
public class ProductsSparePartsSCMapper {

    /**
     * Convert CreateDTO to Entity
     */
    public ProductsSparePartsSC toEntity(ProductsSparePartsSCCreateDTO dto,
                                          ProductsSparePartsTypeSC partType,
                                          WarrantyClaim claim) {
        ProductsSparePartsSC entity = new ProductsSparePartsSC();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setYearOfManufacture(dto.getYearOfManufacture());
        entity.setPrice(dto.getPrice());
        entity.setWarrantyPeriod(dto.getWarrantyPeriod());
        entity.setDescription(dto.getDescription());
        entity.setBrand(dto.getBrand());
        entity.setPartType(partType);
        entity.setClaim(claim);
        return entity;
    }

    /**
     * Convert Entity to ResponseDTO
     */
    public ProductsSparePartsSCResponseDTO toResponseDTO(ProductsSparePartsSC entity) {
        if (entity == null) {
            return null;
        }

        ProductsSparePartsSCResponseDTO dto = ProductsSparePartsSCResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .yearOfManufacture(entity.getYearOfManufacture())
                .price(entity.getPrice())
                .warrantyPeriod(entity.getWarrantyPeriod())
                .description(entity.getDescription())
                .brand(entity.getBrand())
                .build();

        // Map part type info
        if (entity.getPartType() != null) {
            ProductsSparePartsSCResponseDTO.PartTypeInfoDTO partTypeInfo =
                    ProductsSparePartsSCResponseDTO.PartTypeInfoDTO.builder()
                            .id(entity.getPartType().getId())
                            .partName(entity.getPartType().getPartName())
                            .description(entity.getPartType().getDescription())
                            .manufacturer(entity.getPartType().getManufacturer())
                            .build();
            dto.setPartType(partTypeInfo);
        }

        // Map claim info
        if (entity.getClaim() != null) {
            ProductsSparePartsSCResponseDTO.ClaimInfoDTO claimInfo =
                    ProductsSparePartsSCResponseDTO.ClaimInfoDTO.builder()
                            .claimId(entity.getClaim().getClaimId())
                            .customerName(entity.getClaim().getCustomerName())
                            .issueDescription(entity.getClaim().getIssueDescription())
                            .build();
            dto.setClaim(claimInfo);
        }

        return dto;
    }

    /**
     * Update Entity from UpdateDTO
     */
    public void updateEntityFromDTO(ProductsSparePartsSC entity,
                                     ProductsSparePartsSCUpdateDTO dto,
                                     ProductsSparePartsTypeSC partType,
                                     WarrantyClaim claim) {
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getYearOfManufacture() != null) {
            entity.setYearOfManufacture(dto.getYearOfManufacture());
        }
        if (dto.getPrice() != null) {
            entity.setPrice(dto.getPrice());
        }
        if (dto.getWarrantyPeriod() != null) {
            entity.setWarrantyPeriod(dto.getWarrantyPeriod());
        }
        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }
        if (dto.getBrand() != null) {
            entity.setBrand(dto.getBrand());
        }
        if (partType != null) {
            entity.setPartType(partType);
        }
        if (claim != null) {
            entity.setClaim(claim);
        }
    }
}
