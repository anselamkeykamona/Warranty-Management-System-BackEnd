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
@Table(name = "Electric_Vehicle_Type_Recall")
@IdClass(ElectricVehicleTypeRecallId.class)
public class ElectricVehicleTypeRecall {

    @Id
    @Column(name = "ID_Electric_Vehicle_Type", length = 50)
    private String vehicleTypeId;

    @Id
    @Column(name = "Recall_ID", length = 50)
    private String recallId;

    @ManyToOne
    @JoinColumn(name = "ID_Electric_Vehicle_Type", insertable = false, updatable = false)
    private ElectricVehicleType vehicleType;

    @ManyToOne
    @JoinColumn(name = "Recall_ID", insertable = false, updatable = false)
    private Recall recall;
}
