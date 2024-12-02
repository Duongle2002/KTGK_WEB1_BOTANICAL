package com.example.KTCK.service;

import com.example.KTCK.dto.UserDto;
import com.example.KTCK.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}