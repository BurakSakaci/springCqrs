package com.turkcell.elearner.ws.models;

import lombok.Data;

@Data
public class UpdateCourseModel {

	private String courseName;
	private int price;
	private String description;
	
}
