package com.app.handlers;

import com.app.pojo.User;
import com.app.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{
    @Autowired
    private UserService userDetailsService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse reponse, Authentication a) throws IOException, ServletException {
        User u = this.userDetailsService.getUsersToLogin(a.getName()).get(0);
        request.getSession().setAttribute("currentUser", u);
        reponse.sendRedirect("/SpendingManagement/dashboard");
    }       
}
