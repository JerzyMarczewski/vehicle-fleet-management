package com.fleetmanager.vehiclefleetmanagement.dto;

import java.util.UUID;
import com.fleetmanager.vehiclefleetmanagement.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private UUID uuid;
    private String username;
    private UserRole userRole;
}
