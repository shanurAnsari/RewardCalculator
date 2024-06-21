package com.customer.rewards.service;

import com.customer.rewards.entity.User;

import java.util.List;

public interface UserService {

    List<User> listAllUsers();

    User saveUser(User user);

    User getUserById(Integer id);

    void deleteUserById(Integer id);
    
}
