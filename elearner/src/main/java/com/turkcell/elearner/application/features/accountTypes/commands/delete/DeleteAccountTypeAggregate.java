package com.turkcell.elearner.application.features.accountTypes.commands.delete;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class DeleteAccountTypeAggregate {
	
	@AggregateIdentifier
	private String accountTypeId;
	private String accountName;
	private double price;
	private String description;

	public DeleteAccountTypeAggregate() {
		
	}
	
	
	@CommandHandler()
	public DeleteAccountTypeAggregate(DeleteAccountTypeCommand deleteAccountTypeCommand) {
		
		AccountTypeDeletedEvent accountTypeDeletedEvent = new AccountTypeDeletedEvent();
		
		BeanUtils.copyProperties(deleteAccountTypeCommand, accountTypeDeletedEvent);
		
		AggregateLifecycle.apply(accountTypeDeletedEvent);
	}
	
	
	@EventSourcingHandler()
	public void on(AccountTypeDeletedEvent accountTypeDeletedEvent) {
		this.accountName = accountTypeDeletedEvent.getAccountName();
	}
	
	
	
}
