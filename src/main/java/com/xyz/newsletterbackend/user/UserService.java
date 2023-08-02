package com.xyz.newsletterbackend.user;

import com.xyz.newsletterbackend.role.Role;
import com.xyz.newsletterbackend.role.RoleRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;

    public UserDto createUser(UserDto userDto){
        var role = roleRepository.findByName("ADMIN");
        if(role == null){
            role = new Role();
            role.setName("ADMIN");
            roleRepository.save(role);
        }
        var user = modelMapper.map(userDto, User.class);

        // set role
        user.setRole(role);
        // set USER_ID with their name prefix
        var name = user.getName().replaceAll(" ", "").toLowerCase();
        user.setId(name + new ObjectId().toString());

        var savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }
}
