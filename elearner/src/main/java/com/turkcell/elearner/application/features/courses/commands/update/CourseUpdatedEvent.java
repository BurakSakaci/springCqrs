package com.turkcell.elearner.application.features.courses.commands.update;

import lombok.Data;

@Data
public class CourseUpdatedEvent {
	private String courseId;

	private String courseName;

	private int price;

	private String description;
}
