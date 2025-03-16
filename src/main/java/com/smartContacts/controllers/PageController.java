package com.smartContacts.controllers;

import com.smartContacts.entities.User;
import com.smartContacts.forms.UserForm;
import com.smartContacts.helpers.Message;
import com.smartContacts.helpers.MessageType;
import com.smartContacts.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model)
    {
        System.out.println("home page handler");

        //Sending data to view
        model.addAttribute("name","Substring Technologies");
        model.addAttribute("YTChannel","https://youtube.com");
        //home here is the name of the template we return from the templates folder
        return "home";
    }

    //about
    @RequestMapping("/about")
    public String aboutPage(){
        System.out.println("About page loading.");
        return "about";
    }

    //services
    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("services page loading.");
        return "services";
    }

    //contact
    @GetMapping("/contact")
    public String contact(){
        System.out.println("Contact Page loading...");
        return "contact";
    }

    //login
    @GetMapping("/login")
    public String login(){
        System.out.println("Login page loading");
        return "login";
    }

    //login
    @GetMapping("/register")
    public String register(Model model){
        UserForm userForm=new UserForm();
        //default data can be added as well
        model.addAttribute("userForm",userForm);
        return "register";
    }

    //Processing register form
    @RequestMapping(value = "/do-register",method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){
        System.out.println("Processing Registration.");
        //fetch the form data
        //User form
        System.out.println(userForm);
        //validate the form data
        if(rBindingResult.hasErrors()){
            return "register";
        }

        //save to db
        //userService
//        User user=User.builder()
//                .name(userForm.getName())
//                .email(userForm.getEmail())
//                .password(userForm.getPassword())
//                .about(userForm.getAbout())
//                .phoneNumber(userForm.getPhoneNumber())
//                .profilePic(
//                        "https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?semt=ais_hybrid"
//                )
//                .build();
        User user=new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?semt=ais_hybrid");


        User savedUser=userService.saveUser(user);
        System.out.println("The user has been saved.");
        //message= "Registration successful"

        //add the message

        Message message=Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message",message);
        //Redirect to login page
        return "redirect:/register";
    }
}
