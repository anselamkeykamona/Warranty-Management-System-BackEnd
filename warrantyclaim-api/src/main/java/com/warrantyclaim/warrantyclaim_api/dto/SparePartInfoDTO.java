package com.warrantyclaim.warrantyclaim_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SparePartInfoDTO {
    private String partId;
    private String nameProduct;
    private String brand;
    private Float price;
    private Integer warrantyPeriod;
}