package com.app.repository;

import com.app.pojo.Google;
import com.app.pojo.User;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository {

    List<User> getUsersToLogin(String email);

    List<User> getAllUsers();

    List<User> getUsers(Map<String, String> params, int page);

    List<User> getUserByEmail(String email);

    //Google login
    String getToken(final String code) throws ClientProtocolException, IOException;

    Google getUserInfo(final String accessToken) throws ClientProtocolException, IOException;

    String getUser(String accessToken) throws ClientProtocolException, IOException;

    UserDetails buildUser(Google googlePojo);

    int countUsers();

    boolean addUser(User user);

    boolean updateUser(String firstName, String lastName, String email, String phone, int id);

    boolean updatePassword(String password, int id);

    boolean updateActiveUser(int active, int id);

    boolean deleteWallet(int id);

    boolean deleteUserWallet(int id);

    boolean updateUserAvatar(String image, int id);

    void sendEmail(String from, String to, String subject, String content);

    String generateRandomSpecialCharacters(int length);
}
