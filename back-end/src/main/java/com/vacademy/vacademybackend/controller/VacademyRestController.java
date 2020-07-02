package com.vacademy.vacademybackend.controller;

import com.vacademy.vacademybackend.model.*;
import com.vacademy.vacademybackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class VacademyRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ReplyService replyService;

    //Admin
    @GetMapping(value = "/admin/all-admins")
    public List<Admin> getAllAdmin() {
        return adminService.getAllAdmin();
    }

    @GetMapping(value = "/admin/get-admin/{username}")
    public Admin getAdmin(@PathVariable String username) {
        return adminService.getAdmin(username);
    }

    @GetMapping(value = "/admin/user/all-users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/admin/instructor/all-instructors")
    public List<Instructor> getAllInstructors() {
        return instructorService.getAllInstructors();
    }

    @GetMapping(value = "/admin/session/all-sessions")
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @PostMapping(value = "/admin/add-admin")
    public String addAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }

    @Transactional
    @DeleteMapping(value = "/admin/delete-admin/{username}")
    public String deleteAdmin(@PathVariable String username) {
        return adminService.deleteAdmin(username);
    }

    //Courses
    @GetMapping(value = "/common/all-courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping(value = "/course/get-course")
    public Course getCourseByCourseBody(@RequestBody Course course) {
        return courseService.getCourseByCourseBody(course);
    }

    @GetMapping(value = "/course/course-id={id}")
    public Course getCourseById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    @PostMapping(value = "/course/add-course")
    public String addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @Transactional
    @DeleteMapping(value = "/course/delete-course/{id}")
    public String deleteCourse(@PathVariable Integer id) {
        return courseService.deleteCourse(id);
    }

    @PostMapping(value = "course/update/details")
    public Course updateCourseDetails(@RequestBody Course course) {
        return courseService.updateCourseDetails(course);
    }

    @GetMapping(value = "course/update/enrolls/{id}")
    public String updateEnrolls(@PathVariable Integer id) {
        return courseService.updateEnrolls(id);
    }

    //User
    @GetMapping(value = "/user/get-user/{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUser(username);
    }

    @PostMapping(value = "/common/add-user")
    public String createUser(@RequestBody User new_user) {
        return userService.createUser(new_user);
    }

    @PostMapping(value = "/user/update/payment")
    public User updatePayment(@RequestBody User user) {
        return userService.updatePayment(user);
    }

    @PostMapping(value = "/user/update/course-complete")
    public User updateCourseComplete(@RequestBody User user) {
        return userService.updateCourseComplete(user);
    }

    @PostMapping(value = "/user/update/active-course")
    public User updateActiveCourse(@RequestBody User user) {
        return userService.updateActiveCourse(user);
    }

    @PostMapping(value = "/user/update/password")
    public User updatePassword(@RequestBody User user) {
        return userService.updatePassword(user);
    }

    @PostMapping(value = "/user/update/interest")
    public User updateSuggesstion(@RequestBody User user) {
        return userService.updateSuggesstion(user);
    }

    @PostMapping(value = "/user/update/profile")
    public User updateProfile(@RequestBody User user) {
        return userService.updateProfile(user);
    }

    @Transactional
    @DeleteMapping(value = "/user/delete-user/{username}")
    public String deleteUser(@PathVariable String username) {
        return userService.deleteUser(username);
    }

    //Instructor
    @GetMapping(value = "/instructor/{username}")
    public Instructor getInstructor(@PathVariable String username) {
        return instructorService.getInstructor(username);
    }

    @Transactional
    @DeleteMapping(value = "/instructor/delete-instructor/{username}")
    public String deleteInstructor(@PathVariable String username) {
        return instructorService.deleteInstructor(username);
    }

    @PostMapping(value = "/common/add-instructor")
    public String addInstructor(@RequestBody Instructor instructor) {
        return instructorService.addInstructor(instructor);
    }

    @PostMapping(value = "/instructor/update/profile")
    public Instructor updateInstructorProfile(@RequestBody Instructor instructor) {
        return instructorService.updateInstructorProfile(instructor);
    }

    @PostMapping(value = "/instructor/update/password")
    public Instructor updateInstructorPassword(@RequestBody Instructor instructor) {
        return instructorService.updateInstructorPassword(instructor);
    }

    @GetMapping(value = "/instructor//update/course/{username}/{course_code}/{id}")
    public Instructor updateInstructorCourse(@PathVariable String username, @PathVariable String course_code, @PathVariable Integer id) {
        return instructorService.updateInstructorCourse(username, course_code, id);
    }

    //Session
    @GetMapping(value = "/session/{id}")
    public Session getSession(@PathVariable Integer id) {
        return sessionService.getSession(id);
    }

    @PostMapping(value = "/session/add-session")
    public Session addSession(@RequestBody Session session) {
        return sessionService.addSession(session);
    }

    @PostMapping(value = "/session/update")
    public String updateSession(@RequestBody Session session) {
        return sessionService.updateSession(session);
    }

    @Transactional
    @DeleteMapping(value = "/session/delete-session/{id}")
    public String deleteSession(@PathVariable Integer id) {
        return sessionService.deleteSession(id);
    }

    //Question
    @GetMapping(value = "/forum/all-questions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping(value = "/forum/post/question")
    public Question addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    //Reply
    @GetMapping(value = "/forum/all-replies")
    public List<Reply> getAllReplies() {
        return replyService.getAllReplies();
    }

    @PostMapping(value = "/forum/post/reply")
    public Reply addReply(@RequestBody Reply reply) {
        return replyService.addReply(reply);
    }
}