package com.analistas.loginandsignin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.analistas.loginandsignin.model.entity.User;
import com.analistas.loginandsignin.model.repository.UserRepository;


@Controller
public class AppController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping({ "/", "/index" })
    public String viewHomePAge() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {

        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "register_success";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        
        List<User> users = (List<User>) userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }    

}
