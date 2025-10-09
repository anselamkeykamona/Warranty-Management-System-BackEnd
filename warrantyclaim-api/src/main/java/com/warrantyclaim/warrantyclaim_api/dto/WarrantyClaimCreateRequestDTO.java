package com.warrantyclaim.warrantyclaim_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarrantyClaimCreateRequestDTO { // this information for creating   a Warranty Claim

    @NotBlank(message = "Require issue description to fix car!!!")
    private String issueDescription;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate claimDate;

    @Email(message = "Email Invalid")
    private String email;

    @NotBlank(message = "Require Picture for verification!!")
    private String picture;

}
