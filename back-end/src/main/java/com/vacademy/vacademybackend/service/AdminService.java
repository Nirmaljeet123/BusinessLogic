package com.vacademy.vacademybackend.service;

import com.vacademy.vacademybackend.database.AdminRepository;
import com.vacademy.vacademybackend.database.InstructorRepository;
import com.vacademy.vacademybackend.database.SessionRepository;
import com.vacademy.vacademybackend.database.UserRepository;
import com.vacademy.vacademybackend.model.Admin;
import com.vacademy.vacademybackend.model.Instructor;
import com.vacademy.vacademybackend.model.Session;
import com.vacademy.vacademybackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    public Admin getAdmin(String username) {
        return adminRepository.findAdminByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public String addAdmin(Admin admin) {
        String admin_username = admin.getUsername();
        if (adminRepository.existsAdminByUsername(admin_username)) {
            return "Admin Username already exists";
        } else {
            adminRepository.save(admin);
            return admin_username + "Account added Successfully";
        }
    }

    public String deleteAdmin(String username) {
        if (adminRepository.existsAdminByUsername(username)) {
            adminRepository.removeAdminByUsername(username);
            return "Admin Account Removed Successfully";
        } else {
            return "Admin Account not found!";
        }
    }

}
