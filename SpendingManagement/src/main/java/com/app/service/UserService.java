package com.app.service;

import com.app.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    boolean addUser(User user);
    int countUsers();
    List<User> getAllUsers();
    List<User> getUsers(Map<String, String> params, int page);
    List<User> getUserByEmail(String email);
    List<User> getUsersToLogin(String email);
}
