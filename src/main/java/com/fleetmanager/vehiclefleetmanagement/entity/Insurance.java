package com.fleetmanager.vehiclefleetmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "insurance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    private String provider;

    @Column(name = "policy_number")
    private String policyNumber;

    @Column(name = "valid_from")
    private Date validFrom;

    @Column(name = "valid_to")
    private Date validTo;

    private Double cost;
}

