package com.alexp.service;

import com.alexp.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    List<User> getUsersByIds(List<Long> ids);
}
