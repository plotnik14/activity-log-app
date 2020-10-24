package com.alexp.repository;

import com.alexp.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getUsers();
    List<User> getUsersByIds(List<Long> ids);
}
