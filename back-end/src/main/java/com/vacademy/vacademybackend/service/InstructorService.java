package com.vacademy.vacademybackend.service;

import com.vacademy.vacademybackend.database.InstructorRepository;
import com.vacademy.vacademybackend.model.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor getInstructor(String username) {
        return instructorRepository.findInstructorByUsername(username);
    }

    public String deleteInstructor(String username) {
        if (instructorRepository.existsInstructorByUsername(username)) {
            instructorRepository.removeInstructorByUsername(username);
            return "Instructor Account Removed Successfully";
        } else {
            return "Instructor Account not found!";
        }
    }

    public String addInstructor(Instructor instructor) {
        String instructor_username = instructor.getUsername();
        String instructor_email = instructor.getEmail();
        if (instructorRepository.existsInstructorByUsername(instructor_username)) {
            return "Instructor Username already exists";
        } else if (instructorRepository.existsInstructorByEmail(instructor_email)) {
            return "Instructor Email already exists";
        } else {
            instructorRepository.save(instructor);
            return instructor_username + " Account added Successfully";
        }
    }

    public Instructor updateInstructorProfile(Instructor instructor) {
        Instructor edit_instructor = instructorRepository.findInstructorByUsername(instructor.getUsername());
        edit_instructor.setFullname(instructor.getFullname());
        edit_instructor.setUsername(instructor.getUsername());
        edit_instructor.setEmail(instructor.getEmail());
        edit_instructor.setMobile(instructor.getMobile());
        edit_instructor.setQualification(instructor.getQualification());
        instructorRepository.save(edit_instructor);
        return instructorRepository.findInstructorByUsername(instructor.getUsername());
    }

    public Instructor updateInstructorPassword(Instructor instructor) {
        Instructor edit_instructor = instructorRepository.findInstructorByUsername(instructor.getUsername());
        edit_instructor.setPassword(instructor.getPassword());
        instructorRepository.save(edit_instructor);
        return instructorRepository.findInstructorByUsername(instructor.getUsername());
    }

    public Instructor updateInstructorCourse(String username, String course_code, Integer id) {
        try {
            Instructor edit_instructor = instructorRepository.findInstructorByUsername(username);
            edit_instructor.setCourses(edit_instructor.getCourses().concat(course_code + "#" + id + ","));
            instructorRepository.save(edit_instructor);
            return instructorRepository.findInstructorByUsername(username);
        } catch (Exception e) {
            return null;
        }
    }
}
