package com.marvelousmacros.api.service;

import com.marvelousmacros.api.entity.User;
import com.marvelousmacros.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
