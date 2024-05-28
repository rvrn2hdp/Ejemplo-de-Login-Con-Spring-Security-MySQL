package com.analistas.loginandsignin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.analistas.loginandsignin.model.entity.RoleEnum;
import com.analistas.loginandsignin.model.entity.UserEntity;
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

        model.addAttribute("user", new UserEntity());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(UserEntity user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        
        // encriptar contraseña
        user.setPassword(encodedPassword);

        // asignar rol
        user.getRoles().forEach(role -> {
            System.out.println(role);
        });

        userRepository.save(user);
        return "register_success";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        
        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }    

}
