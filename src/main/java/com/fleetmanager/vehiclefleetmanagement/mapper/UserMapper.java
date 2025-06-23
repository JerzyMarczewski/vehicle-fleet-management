package com.fleetmanager.vehiclefleetmanagement.mapper;

import com.fleetmanager.vehiclefleetmanagement.dto.UserDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromDTO(UserDTO dto);

    UserDTO toDTO(User user);
}
