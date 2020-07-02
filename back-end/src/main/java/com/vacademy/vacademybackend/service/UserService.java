package com.vacademy.vacademybackend.service;

import com.vacademy.vacademybackend.database.UserRepository;
import com.vacademy.vacademybackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    public String createUser(User new_user) {
        try {
            String new_user_name = new_user.getUsername();
            String new_user_email = new_user.getEmail();
            String new_user_mobile = new_user.getMobile();
            if (userRepository.existsUserByUsername(new_user_name)) {
                return "Username already exists";
            } else if (userRepository.existsUserByEmail(new_user_email)) {
                return "Email already exists";
            } else if (userRepository.existsUserByMobile(new_user_mobile)) {
                return "Mobile already exists";
            } else {
                userRepository.save(new_user);
                return new_user_name + " Account added Successfully";
            }
        } catch (Exception e) {
            return "Invalid Details! Please provide correct details";
        }
    }

    public User updatePayment(User user) {
        User edit_user = userRepository.findUserByUserid(user.getUserid());
        edit_user.setEnrolled_courses(user.getEnrolled_courses());
        edit_user.setPayment_details(user.getPayment_details());
        userRepository.save(edit_user);
        return userRepository.findUserByUserid(user.getUserid());
    }

    public User updateCourseComplete(User user) {
        User edit_user = userRepository.findUserByUserid(user.getUserid());
        edit_user.setCourses_completed(user.getCourses_completed());
        edit_user.setActive_courses(user.getActive_courses());
        userRepository.save(edit_user);
        return userRepository.findUserByUserid(user.getUserid());
    }

    public User updateActiveCourse(User user) {
        User edit_user = userRepository.findUserByUserid(user.getUserid());
        edit_user.setActive_courses(user.getActive_courses());
        userRepository.save(edit_user);
        return userRepository.findUserByUserid(user.getUserid());
    }

    public User updatePassword(User user) {
        User edit_user = userRepository.findUserByUserid(user.getUserid());
        edit_user.setPassword(user.getPassword());
        userRepository.save(edit_user);
        return userRepository.findUserByUserid(user.getUserid());
    }

    public User updateSuggesstion(User user) {
        User edit_user = userRepository.findUserByUserid(user.getUserid());
        edit_user.setInterest(user.getInterest());
        userRepository.save(edit_user);
        return userRepository.findUserByUserid(user.getUserid());
    }

    public User updateProfile(User user) {
        User edit_user = userRepository.findUserByUserid(user.getUserid());
        edit_user.setFullname(user.getFullname());
        edit_user.setUsername(user.getUsername());
        edit_user.setEmail(user.getEmail());
        edit_user.setMobile(user.getMobile());
        edit_user.setParent_mobile(user.getParent_mobile());
        userRepository.save(edit_user);
        return userRepository.findUserByUserid(user.getUserid());
    }

    public String deleteUser(String username) {
        if (userRepository.existsUserByUsername(username)) {
            userRepository.removeUserByUsername(username);
            return "Account Removed Successfully";
        } else {
            return "Account not found!";
        }
    }
}
