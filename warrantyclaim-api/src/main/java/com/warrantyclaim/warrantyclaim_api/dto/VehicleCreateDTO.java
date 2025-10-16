package com.warrantyclaim.warrantyclaim_api.dto;

import com.warrantyclaim.warrantyclaim_api.entity.ElectricVehicleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleCreateDTO {

    @Pattern(regexp = "^[A-HJ-NPR-Z0-9]{17}$", message = "Invalid VIN format")
    @NotBlank(message = "Vehicle must have a VIN")
    private String vehicleId;

    private String vehicleName;

    @NotBlank(message = "Vehicle must have an owner")
    private String owner;

    @Email(message = "Invalid email Format!!!")
    @NotBlank(message = "Email can not be blank!!!")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    @NotBlank(message = "Vehicle owner must have a phone number for contact information")
    private String phoneNumber;

    @NotBlank(message = "Vehicle must have a type")
    private String electricVehicleTypeId;

    private ElectricVehicleType electricVehicleType;
}
