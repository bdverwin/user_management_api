package com.benhur.user_management.service;

import com.benhur.user_management.dto.UserRequest;
import com.benhur.user_management.dto.UserResponse;
import com.benhur.user_management.entity.User;
import com.benhur.user_management.exceptions.UserNotFoundException;
import com.benhur.user_management.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> saveUser(UserRequest user){
        User newUser = new User();

        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        log.info("Saving user...");
        User created = userRepository.save(newUser);
        UserResponse response = new UserResponse("User created successfully", created.getId(), HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public Optional<User> getUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user;
        }
        throw new UserNotFoundException("User not found with an id of: " + id);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public ResponseEntity<?> deleteUser(long id){
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()){
            String fullName = optionalUser.get().getFirstName() + " " + optionalUser.get().getLastName();
            Long userId = optionalUser.get().getId();
            log.info("Removing user: " + optionalUser.get().getId());
            userRepository.delete(optionalUser.get());
            UserResponse response = new UserResponse("User " + fullName + " has been successfully deleted.", userId, HttpStatus.ACCEPTED.value());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        }
        throw new UserNotFoundException("User not found with an id : " + id);
    }

    public ResponseEntity<?> updateUser(long id, UserRequest userDetails){
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setEmail(userDetails.getEmail());
            log.info("Updating user " + user.getId() + "...");
            userRepository.save(user);
            UserResponse response = new UserResponse("User updated successfully.", user.getId(), HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        throw new UserNotFoundException("User not found with an id : " + id);
    }
}
