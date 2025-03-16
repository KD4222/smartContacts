package com.smartContacts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    //user dashboard
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String userDashboard() {
        return "user/dashboard";
    }

    //user profile page
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String userProfile() {
        System.out.println("User profile");
        return "user/profile";
    }

    //user add contact page

    //user view contacts

    //user edit contacts

    //user delete contact

    //user search contact
}
