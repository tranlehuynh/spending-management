package com.app.controllers;

import com.app.pojo.User;
import com.app.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    @Autowired
    private UserService userService;
    
    @GetMapping("/register")
    public String register(Model model) {
        if (isAuthenticated())
            return "redirect:/dashboard";
        
        model.addAttribute("loginForm", 2);
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute(value = "user") @Valid User user,
            BindingResult rs, Model model) {
        String errorMessagePassword = "";
        
        user.setRole("USER");

        if (user.getPassword().equals(user.getRetypePassword())) {
            if (this.userService.addUser(user) == true) {
                return "redirect:/";
            } else {
                errorMessagePassword = "Error";
            }
        } else {
            errorMessagePassword = "Password not correct!";
        }

        if (rs.hasErrors()) {
            return "register";
        }

        model.addAttribute("errorMessagePassword", errorMessagePassword);
        return "register";
    }

}
