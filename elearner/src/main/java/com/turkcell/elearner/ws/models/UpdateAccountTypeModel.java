package com.turkcell.elearner.ws.models;

import lombok.Data;

@Data
public class UpdateAccountTypeModel {

	private String accountName;
	private double price;
	private String description;
}
