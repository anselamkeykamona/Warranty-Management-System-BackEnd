package com.warrantyclaim.warrantyclaim_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartTypeInfoDTO {
    private String id;
    private String partName;
    private String manufacturer;
    private Float price;
}
