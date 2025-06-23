package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.dto.UserDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserServiceImpl implements UserService{
    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public List<UserDTO> getAllUserDTOs() {
        return List.of();
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> getUserDTOBuId(UUID id) {
        return Optional.empty();
    }

    @Override
    public User createUser(UserDTO newUser) {
        return null;
    }

    @Override
    public UUID deleteUserById(UUID id) {
        return null;
    }

    @Override
    public User editUser(UUID id, UserDTO changedUser) {
        return null;
    }
}
