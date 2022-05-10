package com.turkcell.elearner.application.features.courses.commands;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turkcell.elearner.application.features.accountTypes.commands.delete.AccountTypeDeletedEvent;
import com.turkcell.elearner.application.features.accountTypes.commands.update.AccountTypeUpdatedEvent;
import com.turkcell.elearner.application.features.courses.commands.create.CourseCreatedEvent;
import com.turkcell.elearner.application.features.courses.commands.delete.CourseDeletedEvent;
import com.turkcell.elearner.application.features.courses.commands.update.CourseUpdatedEvent;
import com.turkcell.elearner.domain.AccountType;
import com.turkcell.elearner.domain.Course;
import com.turkcell.elearner.persistence.CourseRepository;

@Component
public class CourseEventsHandler {
	
	private CourseRepository courseRepository;

	@Autowired
	public CourseEventsHandler(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	
	@EventHandler
	public void create(CourseCreatedEvent courseCreatedEvent) {
		
		Course course = new Course();
		BeanUtils.copyProperties(courseCreatedEvent, course);
		this.courseRepository.save(course);
	}
	
	
	@EventHandler
	public void delete(CourseDeletedEvent courseDeletedEvent) {
		
		//AccountType accountType = handleAccountType(accountTypeDeletedEvent.getAccountTypeId());
		
		Course course = courseRepository.findByCourseName(courseDeletedEvent.getCourseName());
		
		this.courseRepository.delete(course);;
	}
	
	@EventHandler
	public void update(CourseUpdatedEvent courseUpdatedEvent) {
		//AccountType accountType = handleAccountType(accountTypeUpdatedEvent.getAccountTypeId());
		
		Course course = courseRepository.findByCourseName(courseUpdatedEvent.getCourseName());
		
		course.setCourseId(courseUpdatedEvent.getCourseId());
		course.setCourseName(courseUpdatedEvent.getCourseName());
		course.setPrice(courseUpdatedEvent.getPrice());
		course.setDescription(course.getDescription());
		
		this.courseRepository.save(course);
	}

}
