package com.app.service.impl;

import com.app.pojo.Google;
import com.app.pojo.User;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

        if (users.isEmpty() || users.get(0).getActive() == 2) {
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
//        user.setRole("USER");
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

    @Override
    public String getToken(final String code) throws ClientProtocolException, IOException{
        return this.userRepository.getToken(code);
    }

    @Override
    public Google getUserInfo(final String accessToken) throws ClientProtocolException, IOException{
        return this.userRepository.getUserInfo(accessToken);
    }

    @Override
    public UserDetails buildUser(Google googlePojo) {
        return this.userRepository.buildUser(googlePojo);
    }

    @Override
    public String getUser(String accessToken) throws ClientProtocolException, IOException {
        return this.userRepository.getUser(accessToken);
    }

    @Override
    @Modifying
    public boolean updateUser(String firstName, String lastName, String email, String phone, int id) {
        return userRepository.updateUser(firstName, lastName, email, phone, id);
    }

    @Override
    public void sendEmail(String from, String to, String subject, String content) {
        this.userRepository.sendEmail(from, to, subject, content);
    }

    @Override
    public boolean updatePassword(String password, int id) {
        return this.userRepository.updatePassword(this.passwordEncoder.encode(password), id);
    }

    @Override
    public boolean updateActiveUser(int active, int id) {
        return this.userRepository.updateActiveUser(active, id);
    }

    @Override
    public boolean deleteWallet(int id) {
        return this.userRepository.deleteWallet(id);
    }

    @Override
    public boolean deleteUserWallet(int id) {
        return this.userRepository.deleteUserWallet(id);
    }

    @Override
    public boolean updateUserAvatar(String image, int id) {
        return this.userRepository.updateUserAvatar(image, id);
    }

    @Override
    public String generateRandomSpecialCharacters(int length) {
        return this.userRepository.generateRandomSpecialCharacters(length);
    }
}
