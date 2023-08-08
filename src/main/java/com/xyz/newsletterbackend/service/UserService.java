package com.xyz.newsletterbackend.service;

import com.xyz.newsletterbackend.dto.UserDto;
import com.xyz.newsletterbackend.exception.ResourceNotFoundException;
import com.xyz.newsletterbackend.model.ERole;
import com.xyz.newsletterbackend.model.Role;
import com.xyz.newsletterbackend.model.User;
import com.xyz.newsletterbackend.repository.RoleRepository;
import com.xyz.newsletterbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public UserDto createUser(UserDto userDto){
        var user = modelMapper.map(userDto, User.class);
        var savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public List<UserDto> retrieveAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto updateUser(UserDto userDto, Long id) {
        var user = userRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("User not exists with id " + id));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }


}
