package com.warrantyclaim.warrantyclaim_api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkAssignedInfoDTO {

    private String workAssignId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime workAssignDate;

    private String technicianName;
    private String technicianId;
}
