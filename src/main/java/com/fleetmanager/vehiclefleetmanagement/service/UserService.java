package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.dto.UserDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers();
    List<UserDTO> getAllUserDTOs();
    Optional<User> getUserById(UUID id);
    Optional<UserDTO> getUserDTOBuId(UUID id);

    User createUser(UserDTO newUser);

    UUID deleteUserById(UUID id);

    User editUser(UUID id, UserDTO changedUser);
}
