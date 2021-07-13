package com.taesookim.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.taesookim.project.models.Course;
import com.taesookim.project.repositories.CourseRepository;

@Service
public class CourseService {
	private final CourseRepository courseRepository;
	public CourseService(CourseRepository courseRepo) {
		this.courseRepository = courseRepo;
	}
	public void save(Course course) {
		courseRepository.save(course);
	}
	public List<Course> findAll(){
		return courseRepository.findAll();
	}
	public Course findById(Long id) {
		Optional<Course> course = courseRepository.findById(id);
		if (course.isPresent()){
			return course.get();
		}
		else {
			return null;
		}
	}
	public void saveEdit(Course course) {
		Course oldCourse = this.findById(course.getId());
		oldCourse.setName(course.getName());
		oldCourse.setInstructor(course.getInstructor());
		oldCourse.setCapacity(course.getCapacity());
		this.save(oldCourse);
	}
	
	public void deleteById(Long id) { 
		courseRepository.deleteById(id);
	}
}
