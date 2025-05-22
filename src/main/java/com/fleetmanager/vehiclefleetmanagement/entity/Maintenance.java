package com.fleetmanager.vehiclefleetmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "maintenance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    private Date date;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double cost;

    @Enumerated(EnumType.STRING)
    private MaintenanceType type;
}

enum MaintenanceType {
    INSPECTION,
    REPAIR
}

