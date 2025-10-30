package com.sam.expensetrackapp.controller;

import com.sam.expensetrackapp.model.User;
import com.sam.expensetrackapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/register")
    public String getRegisterForm(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/register")
    public String submitRegisterForm(@ModelAttribute("user") User user){
        userService.SaveUser(user);
        return "login";
    }

    @GetMapping("/login")
    public String getLoginForm(){
        return "login";
    }


}
