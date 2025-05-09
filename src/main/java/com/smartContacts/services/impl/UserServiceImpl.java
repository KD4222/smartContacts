package com.smartContacts.services.impl;

import com.smartContacts.entities.User;
import com.smartContacts.helpers.AppConstants;
import com.smartContacts.helpers.ResourceNotFoundException;
import com.smartContacts.repositories.UserRepo;
import com.smartContacts.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        //user id have to be generated
        String userId= UUID.randomUUID().toString();
        user.setUserId(userId);
        //encode password
        //user.setPassword();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //set the user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));

        logger.info(user.getProvider().toString());
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2=userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found."));
        //update user2 from user info received
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setAbout(user.getAbout());
        user2.setContacts(user.getContacts());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());

        //save user in db
        User save=userRepo.save(user2);
        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        User user2=userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found."));
        userRepo.delete(user2);
    }

    @Override
    public boolean isUserExist(String userId) {
       User user=userRepo.findById(userId).orElse(null);
        return user != null? true:false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user=userRepo.findByEmail(email).orElse(null);
        return user != null ? true:false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
