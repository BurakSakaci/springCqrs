package com.turkcell.elearner.application.features.courses.commands.update;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCourseCommand {

	@TargetAggregateIdentifier
	private String courseId;

	private String courseName;

	private int price;

	private String description;
}
