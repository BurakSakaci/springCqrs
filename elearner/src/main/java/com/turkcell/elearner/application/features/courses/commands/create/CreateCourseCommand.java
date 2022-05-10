package com.turkcell.elearner.application.features.courses.commands.create;

import javax.persistence.Column;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCourseCommand {

	@TargetAggregateIdentifier
	private String courseId;

	private String courseName;

	private int price;

	private String description;

}
