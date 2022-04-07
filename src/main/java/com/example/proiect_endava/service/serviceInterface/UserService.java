package com.example.proiect_endava.service.serviceInterface;

import com.example.proiect_endava.dto.UserDto;
import com.example.proiect_endava.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
     User save(UserDto userDto);
}
