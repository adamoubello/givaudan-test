package com.adamoubello.givaudantest.service;

import com.adamoubello.givaudantest.model.User;
import com.adamoubello.givaudantest.model.UserDto;

import java.util.List;

public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User update(User user);
    User findByEmail(String email);
    User findById(Long id);
}
