package com.warrantyclaim.warrantyclaim_api.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Recall_SC_Technician")
@IdClass(RecallSCTechnicianId.class)
public class RecallSCTechnician {

    @Id
    @Column(name = "Recall_ID", length = 50)
    private String recallId;

    @Id
    @Column(name = "SC_TechnicianID", length = 50)
    private String technicianId;

    @Column(name = "AssignedDate")
    private LocalDateTime assignedDate;

    @ManyToOne
    @JoinColumn(name = "Recall_ID", insertable = false, updatable = false)
    private Recall recall;

    @ManyToOne
    @JoinColumn(name = "SC_TechnicianID", insertable = false, updatable = false)
    private SCTechnician technician;
}
