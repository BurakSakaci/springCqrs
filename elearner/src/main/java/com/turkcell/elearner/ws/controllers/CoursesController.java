package com.turkcell.elearner.ws.controllers;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.elearner.application.features.courses.commands.create.CreateCourseCommand;
import com.turkcell.elearner.application.features.courses.commands.delete.DeleteCourseCommand;
import com.turkcell.elearner.application.features.courses.commands.update.UpdateCourseCommand;
import com.turkcell.elearner.ws.models.CreateCourseModel;
import com.turkcell.elearner.ws.models.DeleteAccountTypeModel;
import com.turkcell.elearner.ws.models.DeleteCourseModel;
import com.turkcell.elearner.ws.models.UpdateAccountTypeModel;
import com.turkcell.elearner.ws.models.UpdateCourseModel;

@RestController
@RequestMapping("/courses")
public class CoursesController {
	private CommandGateway commandGateway;

	public CoursesController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}
	
	@PostMapping("/addCourse")
	public void CreateCourse(@RequestBody CreateCourseModel createCourseModel) {
		
		CreateCourseCommand command = CreateCourseCommand.builder()
				.courseName(createCourseModel.getCourseName())
				.description(createCourseModel.getDescription())
				.price(createCourseModel.getPrice()).build();
		
		command.setCourseId(UUID.randomUUID().toString());
		
		this.commandGateway.sendAndWait(command);
	}
	
	
	@PutMapping("/updateCourse")
	public void UpdateCourse(@RequestBody UpdateCourseModel updateCourseModel) {

		UpdateCourseCommand command = UpdateCourseCommand.builder().courseName(updateCourseModel.getCourseName())
				.price(updateCourseModel.getPrice()).description(updateCourseModel.getDescription())
				.build();

		this.commandGateway.sendAndWait(command);
	}
	
	@DeleteMapping("/deleteCoursee")
	public void DeleteAccountType(@RequestBody DeleteCourseModel deleteCourseModel) {

		DeleteCourseCommand command = DeleteCourseCommand.builder()
				.courseName(deleteCourseModel.getCourseName())
				.build();
	
		this.commandGateway.sendAndWait(command);
	}
	
	
	
	
	
}
