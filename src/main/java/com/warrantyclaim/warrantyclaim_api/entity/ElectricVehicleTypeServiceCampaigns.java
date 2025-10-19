package com.warrantyclaim.warrantyclaim_api.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;





@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Electric_Vehicle_Type_Service_Campaigns")
@IdClass(ElectricVehicleTypeServiceCampaignsId.class)
public class ElectricVehicleTypeServiceCampaigns {

    @Id
    @Column(name = "ID_Electric_Vehicle_Type", length = 50)
    private String vehicleTypeId;

    @Id
    @Column(name = "CampaignsID", length = 50)
    private String campaignId;

    @ManyToOne
    @JoinColumn(name = "ID_Electric_Vehicle_Type", insertable = false, updatable = false)
    private ElectricVehicleType vehicleType;

    @ManyToOne
    @JoinColumn(name = "CampaignsID", insertable = false, updatable = false)
    private ServiceCampaigns campaign;
}
