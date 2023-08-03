package com.xyz.newsletterbackend.service;

import com.xyz.newsletterbackend.dto.UserDto;
import com.xyz.newsletterbackend.exception.ResourceNotFoundException;
import com.xyz.newsletterbackend.model.ERole;
import com.xyz.newsletterbackend.model.Role;
import com.xyz.newsletterbackend.model.User;
import com.xyz.newsletterbackend.repository.RoleRepository;
import com.xyz.newsletterbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
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
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new ResourceNotFoundException("error: Role not found")));
        var user = modelMapper.map(userDto, User.class);

        // set role
        user.setRoles(roles);

        // set password
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // set USER_ID with their name prefix
        user.setId(generateUniqueIdWithUsername(user.getUsername() )); // Ex: samwilson64c9fb671f704d14edeeed20

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
    public UserDto updateUser(UserDto userDto, String id) {
        var user = userRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("User not exists with id " + id));
        user.setId(
                replaceNameInExistingId(
                        user.getId(),
                        user.getUsername(), // Existing username
                        userDto.getUsername() // New username
                )
        );
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    // If repository update their name, I need to replace new name in existing ID.
    // Ex: New name - "patrick" and existing ID samwilson64c9fb671f704d14edeeed20
    // New ID will be patrick64c9fb671f704d14edeeed20
    // I don't have to call generateUniqueIdWithUsername() because I want to change in ID with new name.
    private static String replaceNameInExistingId(String id, String existingUsername, String newUsername){
        String newId = id.replace(getNameInLowerCaseAndWithoutSpaces(existingUsername),
                getNameInLowerCaseAndWithoutSpaces(newUsername));
        return newId;
    }
    private static String generateUniqueIdWithUsername(String username) {
        var USER_NAME_WITHOUT_SPACES = getNameInLowerCaseAndWithoutSpaces(username);
        return USER_NAME_WITHOUT_SPACES + new ObjectId().toString();
    }

    private static String getNameInLowerCaseAndWithoutSpaces(String username) {
        return username.replaceAll(" ", "").toLowerCase();
    }
}
