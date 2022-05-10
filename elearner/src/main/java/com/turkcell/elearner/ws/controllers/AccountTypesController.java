package com.turkcell.elearner.ws.controllers;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.elearner.application.features.accountTypes.commands.create.CreateAccountTypeCommand;
import com.turkcell.elearner.application.features.accountTypes.commands.delete.DeleteAccountTypeCommand;
import com.turkcell.elearner.application.features.accountTypes.commands.update.UpdateAccountTypeCommand;
import com.turkcell.elearner.ws.models.CreateAccountTypeModel;
import com.turkcell.elearner.ws.models.DeleteAccountTypeModel;
import com.turkcell.elearner.ws.models.UpdateAccountTypeModel;

@RestController
@RequestMapping("/accounttypes")
public class AccountTypesController {
	// güvenlikten geçmek gibi
	private CommandGateway commandGateway;

	public AccountTypesController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@PostMapping("/addAccountType")
	public void CreateAccountType(@RequestBody CreateAccountTypeModel createAccountTypeModel) {

		CreateAccountTypeCommand command = CreateAccountTypeCommand.builder().price(createAccountTypeModel.getPrice())
				.accountName(createAccountTypeModel.getAccountName())
				.description(createAccountTypeModel.getDescription()).build();

		command.setAccountTypeId(UUID.randomUUID().toString());

		this.commandGateway.sendAndWait(command);
	}

	@PutMapping("/updateAccountType")
	public void UpdateAccountType(@RequestBody UpdateAccountTypeModel updateAccountTypeModel) {

		UpdateAccountTypeCommand command = UpdateAccountTypeCommand.builder().price(updateAccountTypeModel.getPrice())
				.accountName(updateAccountTypeModel.getAccountName())
				.description(updateAccountTypeModel.getDescription())
				.build();

		this.commandGateway.sendAndWait(command);
	}
	
	@DeleteMapping("/deleteAccountType")
	public void DeleteAccountType(@RequestBody DeleteAccountTypeModel deleteAccountTypeModel) {

		DeleteAccountTypeCommand command = DeleteAccountTypeCommand.builder()
				.accountName(deleteAccountTypeModel.getAccountName())
				.build();
		this.commandGateway.sendAndWait(command);
	}
	
	
	
	

}
