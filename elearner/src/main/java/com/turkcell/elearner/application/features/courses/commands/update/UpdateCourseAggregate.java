package com.turkcell.elearner.application.features.courses.commands.update;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class UpdateCourseAggregate {
	
	@AggregateIdentifier
	private String courseId;
	private String courseName;
	private int price;
	private String description;

	public UpdateCourseAggregate() {
		
	}
	
	@CommandHandler
	public UpdateCourseAggregate (UpdateCourseCommand updateCourseCommand) {
		CourseUpdatedEvent courseUpdatedEvent = new CourseUpdatedEvent();
		
		BeanUtils.copyProperties(updateCourseCommand, courseUpdatedEvent);
		
		AggregateLifecycle.apply(courseUpdatedEvent);
		
	}
	
	@EventSourcingHandler
	public void on(CourseUpdatedEvent courseUpdatedEvent) {
		this.courseId = courseUpdatedEvent.getCourseId();
		this.price = courseUpdatedEvent.getPrice();
		this.description = courseUpdatedEvent.getDescription();
		this.courseName = courseUpdatedEvent.getCourseName();
	}
	
	
	
	
	
}
