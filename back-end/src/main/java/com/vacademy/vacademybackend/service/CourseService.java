package com.vacademy.vacademybackend.service;

import com.vacademy.vacademybackend.database.CourseRepository;
import com.vacademy.vacademybackend.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseByCourseBody(Course course) {
        String name = course.getCoursename();
        String author = course.getAuthor();
        return courseRepository.findCourseByCoursenameAndAuthor(name, author);
    }

    public Course getCourseById(Integer id) {
        return courseRepository.findCourseByCourseid(id);
    }

    public String addCourse(Course course) {
        try {
            String course_code = course.getCoursecode();
            String course_name = course.getCoursename();
            for (Course courses : courseRepository.findAll()) {
                if (courses.getCoursecode().equals(course_code)) {
                    return "Course Code already exists";
                } else if (courses.getCoursename().equals(course_name)) {
                    return "Course Name already exists";
                }
            }
            courseRepository.save(course);
            Course code_course = courseRepository.findCourseByCoursecode(course.getCoursecode());
            code_course.setCoursecode(code_course.getCoursecode().concat(Integer.toString(code_course.getCourseid())));
            courseRepository.save(code_course);
            return course_name + " added Successfully";
        } catch (Exception e) {
            return "Error! Course not added!";
        }
    }

    public String deleteCourse(Integer id) {
        if (courseRepository.existsCourseByCourseid(id)) {
            courseRepository.removeCourseByCourseid(id);
            return "Course Removed Successfully";
        } else {
            return "Course not found!";
        }
    }

    public Course updateCourseDetails(Course course) {
        Course edit_course = courseRepository.findCourseByCoursecode(course.getCoursecode());
        edit_course.setCoursename(course.getCoursename());
        edit_course.setDescrp(course.getDescrp());
        edit_course.setAuthor(course.getAuthor());
        edit_course.setTags(course.getTags());
        edit_course.setTest(course.getTest());
        edit_course.setChapters(course.getChapters());
        edit_course.setVideos(course.getVideos());
        edit_course.setImage(course.getImage());
        edit_course.setPrice(course.getPrice());
        edit_course.setUpdated_on(course.getUpdated_on());
        try {
            courseRepository.save(edit_course);
            return courseRepository.findCourseByCoursecode(course.getCoursecode());
        } catch (Exception e) {
            return null;
        }
    }

    public String updateEnrolls(Integer id) {
        Course edit_course = courseRepository.findCourseByCourseid(id);
        try {
            int x = Integer.parseInt(edit_course.getEnrolls()) + 1;
            edit_course.setEnrolls(Integer.toString(x));
            courseRepository.save(edit_course);
            return "Enrolls updated successfully";
        } catch (Exception e) {
            return "Enrolls not updated!";
        }
    }
}
