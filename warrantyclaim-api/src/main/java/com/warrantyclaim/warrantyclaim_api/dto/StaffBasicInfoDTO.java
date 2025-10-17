package com.warrantyclaim.warrantyclaim_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffBasicInfoDTO {
    private String staffId;
    private String accountName;
    private String email;
    private String phoneNumber;
}