package com.turkcell.elearner.application.features.courses.commands.create;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class CreateCourseAggregate {

	@AggregateIdentifier
	private String courseId;

	private String courseName;

	private int price;

	private String description;

	public CreateCourseAggregate() {

	}

	@CommandHandler()
	public CreateCourseAggregate(CreateCourseCommand command) {

		CourseCreatedEvent courseCreatedEvent = new CourseCreatedEvent();
		
		BeanUtils.copyProperties(command, courseCreatedEvent);
		
		AggregateLifecycle.apply(courseCreatedEvent);
	}
	
	
	@EventSourcingHandler
	public void on(CourseCreatedEvent courseCreatedEvent) {
		
		this.courseId = courseCreatedEvent.getCourseId();
		this.courseName = courseCreatedEvent.getCourseName();
		this.price = courseCreatedEvent.getPrice();
		this.description = courseCreatedEvent.getDescription();
	}

}
