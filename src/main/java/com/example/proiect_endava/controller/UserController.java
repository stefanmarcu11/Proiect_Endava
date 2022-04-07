package com.example.proiect_endava.controller;

import com.example.proiect_endava.dto.UserDto;
import com.example.proiect_endava.service.serviceInterface.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signUp")
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    public UserController(UserService userService, BCryptPasswordEncoder encoder) {
        super();
        this.userService = userService;
        this.encoder = encoder;
    }

    @ModelAttribute("user")
    public UserDto userRegistration() {
        return new UserDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "SignUp";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        userService.save(userDto);
        return "redirect:/signUp?success";
    }


}
