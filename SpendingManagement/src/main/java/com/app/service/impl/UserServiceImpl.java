package com.app.service.impl;

import com.app.pojo.User;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public List<User> getUsers(Map<String, String> params, int page) {
        return this.userRepository.getUsers(params, page);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<User> users = userRepository.getUsersToLogin(email);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("Users does not exist");
        }

        User user = users.get(0);

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public int countUsers() {
        return this.userRepository.countUsers();
    }

    @Override
    public List<User> getUsersToLogin(String email) {
        return this.userRepository.getUsersToLogin(email);
    }

    @Override
    public boolean addUser(User user) {
        String pass = user.getPassword();
        user.setPassword(this.passwordEncoder.encode(pass));
        user.setRole("USER");
        user.setAvatar("https://i.pinimg.com/564x/83/2a/77/832a77b710db7d8d54badb01fb264dc1.jpg");
        user.setActive(1);

        return this.userRepository.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.getAllUsers();
    }

    @Override
    public List<User> getUserByEmail(String email) {
        return this.userRepository.getUserByEmail(email);
    }
}
