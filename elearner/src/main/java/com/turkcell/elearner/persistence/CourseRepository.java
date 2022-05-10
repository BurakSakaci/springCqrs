package com.turkcell.elearner.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.elearner.domain.Course;

public interface CourseRepository extends JpaRepository<Course, String>{
	Course findByCourseName(String courseName);

}
