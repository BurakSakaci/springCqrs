package com.turkcell.elearner.application.features.accountTypes.commands.update;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.turkcell.elearner.domain.AccountType;
import com.turkcell.elearner.persistence.CourseRepository;

@Aggregate
public class UpdateAccountTypeAggregate {

	@AggregateIdentifier
	private String accountTypeId;
	private String accountName;
	private double price;
	private String description;

	public UpdateAccountTypeAggregate() {

	}

	@CommandHandler()
	public UpdateAccountTypeAggregate(UpdateAccountTypeCommand updateAccountTypeCommand) {

		AccountTypeUpdatedEvent accountTypeUpdatedEvent = new AccountTypeUpdatedEvent();

		BeanUtils.copyProperties(updateAccountTypeCommand, accountTypeUpdatedEvent);

		AggregateLifecycle.apply(accountTypeUpdatedEvent);
		
		System.out.println("Command Handler a girdi.");

	}

	@EventSourcingHandler() 
	public void on(AccountTypeUpdatedEvent accountTypeUpdatedEvent) {
		
	  this.accountTypeId = accountTypeUpdatedEvent.getAccountTypeId();
	  this.accountName = accountTypeUpdatedEvent.getAccountName();  
	  this.description = accountTypeUpdatedEvent.getDescription();
	  this.price = accountTypeUpdatedEvent.getPrice();
	  
	  System.out.println("Event Sourcing Handler a girdi.");
	  
	  }

	
	 

}
