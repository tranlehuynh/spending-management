package com.app.controllers;

import com.app.pojo.User;
import com.app.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/user")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(this.userService.getUsers(null, 0), HttpStatus.OK);
    }  
}
