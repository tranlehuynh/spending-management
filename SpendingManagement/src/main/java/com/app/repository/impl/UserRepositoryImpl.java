package com.app.repository.impl;

import com.app.pojo.Google;
import com.app.pojo.User;
import com.app.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@PropertySource("classpath:messages.properties")
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Environment env;
    @Autowired
    private JavaMailSender mailSender;

    public static String GOOGLE_CLIENT_ID = "36810703474-djoro0ttsa9girdk8408dfqe76jb4ga9.apps.googleusercontent.com";
    public static String GOOGLE_CLIENT_SECRET = "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";
    public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/SpendingManagement";
    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static String GOOGLE_GRANT_TYPE = "authorization_code";

    @Override
    public List<User> getUsers(Map<String, String> params, int page) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root root = q.from(User.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate p = b.like(root.get("name").as(String.class), String.format("%%%s%%", kw));
                predicates.add(p);
            }
            q.where(predicates.toArray(Predicate[]::new));
        }

        Query query = s.createQuery(q);
        if (page > 0) {
            int size = Integer.parseInt(env.getProperty("page.size").toString());
            int start = (page - 1) * size;
            query.setFirstResult(start);
            query.setMaxResults(size);

        }
        return query.getResultList();
    }

    @Override
    public int countUsers() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("SELECT COUNT(*) FROM User");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addUser(User user) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getUsersToLogin(String email) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root root = q.from(User.class);
        q.select(root);

        if (!email.isEmpty()) {
            Predicate p = b.equal(root.get("email").as(String.class), email.trim());
            q = q.where(p);
        }
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<User> getAllUsers() {
        Session s = sessionFactory.getObject().getCurrentSession();
        javax.persistence.Query q = s.createQuery("FROM User");
        return q.getResultList();
    }

    @Override
    public List<User> getUserByEmail(String email) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM User WHERE email = '" + email + "'");
        return q.getResultList();
    }

    @Override
    public String getToken(final String code) throws ClientProtocolException, IOException {
        String response = Request.Post(GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", GOOGLE_CLIENT_ID)
                        .add("client_secret", GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response).get("access_token");
        return node.textValue();
    }

    @Override
    public Google getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        ObjectMapper mapper = new ObjectMapper();
        Google googlePojo = mapper.readValue(response, Google.class);
        return googlePojo;
    }

    @Override
    public UserDetails buildUser(Google googlePojo) {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("GOOGLE_USER"));
        UserDetails userDetail = new org.springframework.security.core.userdetails.User(googlePojo.getEmail(),
                "", enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        return userDetail;
    }

    @Override
    public String getUser(String accessToken) throws ClientProtocolException, IOException {
        String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        return response;
    }

    @Override
    @Modifying
    public boolean updateUser(String firstName, String lastName, String email, String phone, int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query query = session.createQuery("UPDATE User SET firstName = '" + firstName + "', lastName = '" + lastName + "', email = '" + email + "', "
                + "phone = '" + phone + "' WHERE id = " + id);
        query.executeUpdate();
        return true;
    }

    @Override
    public void sendEmail(String from, String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        mailSender.send(mailMessage);
    }

    @Override
    public boolean updatePassword(String password, int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query query = session.createQuery("UPDATE User SET password = '" + password + "' WHERE id = " + id);
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean updateActiveUser(int active, int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query query = session.createQuery("UPDATE User SET active = " + active + " WHERE id = " + id);
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean deleteUserWallet(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query query = session.createQuery("DELETE FROM UserWallet WHERE walletId.id = " + id);
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean deleteWallet(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query query = session.createQuery("DELETE FROM Wallet WHERE id = " + id);
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean updateUserAvatar(String image, int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query query = session.createQuery("UPDATE FROM User SET avatar = '" + image + "' WHERE id = " + id);
        query.executeUpdate();
        return true;
    }

    @Override
    public String generateRandomSpecialCharacters(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45)
                .build();
        return pwdGenerator.generate(length);
    }
}
