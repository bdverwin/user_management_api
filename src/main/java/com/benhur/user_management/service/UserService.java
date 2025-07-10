package com.benhur.user_management.service;

import com.benhur.user_management.entity.User;
import com.benhur.user_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUser(Long id){
        return userRepository.findById(id);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public String deleteUser(long id){
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()){
            String fullName = optionalUser.get().getFirstName() + " " + optionalUser.get().getLastName();
            userRepository.delete(optionalUser.get());
            return "User " + fullName + " has been successfully deleted.";
        }
        throw new RuntimeException("User not found with id : " + id);
    }

    public User updateUser(long id, User userDetails){
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        }
        throw new RuntimeException("An error occurred.");
    }
}
