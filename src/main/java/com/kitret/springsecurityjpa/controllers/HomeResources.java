package com.kitret.springsecurityjpa.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResources {
    
    @GetMapping(path = "/")
    public String homePage(){
        return "<h1>Welcome</h1>";
    }

    @GetMapping(path = "/user")
    public String userPage(){
        return "<h1>Welcome User</h1>";
    }

    @GetMapping(path = "/admin")
    public String adminPage(){
        return "<h1>Welcome Admin</h1>";
    }
}
