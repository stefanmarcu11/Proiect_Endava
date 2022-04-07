package com.example.proiect_endava.service;

import com.example.proiect_endava.dto.CustomUserDetails;
import com.example.proiect_endava.dto.UserDto;
import com.example.proiect_endava.entity.Authority;
import com.example.proiect_endava.entity.User;
import com.example.proiect_endava.repository.UserRepository;
import com.example.proiect_endava.service.serviceInterface.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(@Lazy UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getFirstName(),
                userDto.getLastName(), userDto.getEmail(),userDto.getPhone(),
                userDto.getPassword(), new Authority("ROLE_USER"));

        return userRepository.save(user);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new CustomUserDetails(user);
    }

//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Authority> roles){
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }
}
