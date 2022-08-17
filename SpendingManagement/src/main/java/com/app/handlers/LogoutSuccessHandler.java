package com.app.handlers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler{

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse reponse, Authentication a) throws IOException, ServletException {
        request.getSession().removeAttribute("currentUser");
        reponse.sendRedirect("/SpendingManagement/");
    }
}
