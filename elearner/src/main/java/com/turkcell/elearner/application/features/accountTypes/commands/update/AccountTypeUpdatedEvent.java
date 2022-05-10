package com.turkcell.elearner.application.features.accountTypes.commands.update;

import lombok.Data;

@Data
public class AccountTypeUpdatedEvent {
	private String accountTypeId;
	private String accountName;
	private double price;
	private String description;
	
}
