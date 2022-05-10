package com.turkcell.elearner.application.features.courses.commands.delete;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class DeleteCourseAggregate {
	
	@AggregateIdentifier
	private String courseName;

	public DeleteCourseAggregate() {
		
	}
	
	@CommandHandler
	public DeleteCourseAggregate(DeleteCourseCommand deleteCourseCommand) {
		CourseDeletedEvent courseDeletedEvent = new CourseDeletedEvent();
		
		BeanUtils.copyProperties(deleteCourseCommand, courseDeletedEvent);
		
		AggregateLifecycle.apply(courseDeletedEvent);
	}
	
	@EventSourcingHandler
	public void on(CourseDeletedEvent courseDeletedEvent) {
		this.courseName = courseDeletedEvent.getCourseName();
	}
	
	
	
}
