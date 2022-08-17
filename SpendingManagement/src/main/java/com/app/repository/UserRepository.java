package com.app.repository;

import com.app.pojo.User;
import java.util.List;
import java.util.Map;

public interface UserRepository {
    boolean addUser(User user);
    List<User> getUsersToLogin(String email);
    List<User> getUsers(Map<String, String> params, int page);
    int countUsers();
}
