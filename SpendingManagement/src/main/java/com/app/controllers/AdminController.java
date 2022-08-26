package com.app.controllers;

import com.app.pojo.User;
import com.app.service.UserService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@PropertySource("classpath:messages.properties")
public class AdminController {
    
    @Autowired
    private UserService userService;
    @Autowired
    Environment env;
   
    @GetMapping("/user-manage")
    public String list(Model model, @RequestParam Map<String, String> params) {
        
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        model.addAttribute("users", this.userService.getUsers(params, page));
        model.addAttribute("allUsers", userService.getAllUsers());
        
        model.addAttribute("pageSize", Integer.parseInt(env.getProperty("page.size")));
        return "user";
    }
    
    @PostMapping("/user-manage")
    public String activeUser(Model model, @ModelAttribute(value = "activeUser") @Valid User u, BindingResult rs) {
        
        if (rs.hasErrors()) {
            return "index";
        }
        
        if (userService.updateActiveUser(u.getActive(), u.getId()) == true) {
            return "redirect:/admin/user-manage";
        }
        
        return "user";
    }
}