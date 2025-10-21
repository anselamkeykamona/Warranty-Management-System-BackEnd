package com.warrantyclaim.warrantyclaim_api.mapper;

import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsEVMCreateDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsEVMResponseDTO;
import com.warrantyclaim.warrantyclaim_api.dto.ProductsSparePartsEVMUpdateDTO;
import com.warrantyclaim.warrantyclaim_api.entity.ProductsSparePartsEVM;
import com.warrantyclaim.warrantyclaim_api.entity.ProductsSparePartsTypeEVM;
import org.springframework.stereotype.Component;

@Component
public class ProductsSparePartsEVMMapper {

    /**
     * Convert CreateDTO to Entity
     */
    public ProductsSparePartsEVM toEntity(ProductsSparePartsEVMCreateDTO dto,
                                           ProductsSparePartsTypeEVM partType) {
        ProductsSparePartsEVM entity = new ProductsSparePartsEVM();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setYearOfManufacture(dto.getYearOfManufacture());
        entity.setBrand(dto.getBrand());
        entity.setPrice(dto.getPrice());
        entity.setWarrantyPeriod(dto.getWarrantyPeriod());
        entity.setDescription(dto.getDescription());
        entity.setPartType(partType);
        return entity;
    }

    /**
     * Convert Entity to ResponseDTO
     */
    public ProductsSparePartsEVMResponseDTO toResponseDTO(ProductsSparePartsEVM entity) {
        if (entity == null) {
            return null;
        }

        ProductsSparePartsEVMResponseDTO dto = ProductsSparePartsEVMResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .yearOfManufacture(entity.getYearOfManufacture())
                .brand(entity.getBrand())
                .price(entity.getPrice())
                .warrantyPeriod(entity.getWarrantyPeriod())
                .description(entity.getDescription())
                .build();

        // Map part type info
        if (entity.getPartType() != null) {
            ProductsSparePartsEVMResponseDTO.PartTypeInfoDTO partTypeInfo =
                    ProductsSparePartsEVMResponseDTO.PartTypeInfoDTO.builder()
                            .id(entity.getPartType().getId())
                            .partName(entity.getPartType().getPartName())
                            .description(entity.getPartType().getDescription())
                            .manufacturer(entity.getPartType().getManufacturer())
                            .build();
            dto.setPartType(partTypeInfo);
        }

        return dto;
    }

    /**
     * Update Entity from UpdateDTO
     */
    public void updateEntityFromDTO(ProductsSparePartsEVM entity,
                                     ProductsSparePartsEVMUpdateDTO dto,
                                     ProductsSparePartsTypeEVM partType) {
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getYearOfManufacture() != null) {
            entity.setYearOfManufacture(dto.getYearOfManufacture());
        }
        if (dto.getBrand() != null) {
            entity.setBrand(dto.getBrand());
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
        if (partType != null) {
            entity.setPartType(partType);
        }
    }
}
