package com.turkcell.elearner.application.features.accountTypes.commands.create;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class CreateAccountTypeAggregeate {
	@AggregateIdentifier
	private String accountTypeId;
	private String accountName;
	private double price;
	private String description;
	
	public CreateAccountTypeAggregeate() {
		
	}
	
	// createAccountTypeAggregeate olabilir
	@CommandHandler()
	public CreateAccountTypeAggregeate(CreateAccountTypeCommand createAccountTypeCommand) {
		//Business Kodları yazılır
		
		AccountTypeCreatedEvent accountTypeCreatedEvent = new AccountTypeCreatedEvent();
		
		//modelmapper yerine
		BeanUtils.copyProperties(createAccountTypeCommand, accountTypeCreatedEvent);
		
		AggregateLifecycle.apply(accountTypeCreatedEvent);
	}
	
	//axon kurmak gerekebilir
	//dockera baglanamadigindan hata veriyor dockerize edilmesi gerekiyor
	@EventSourcingHandler
	public void on(AccountTypeCreatedEvent accountTypeCreatedEvent) {
		// event sourcing code
		
		this.accountTypeId = accountTypeCreatedEvent.getAccountTypeId();
		this.accountName = accountTypeCreatedEvent.getAccountName();
		this.description = accountTypeCreatedEvent.getDescription();
		this.price = accountTypeCreatedEvent.getPrice();
		
	}
	
	// command aggregate sonra evet oluşturup lifcycle ekler
}


//class isimlerine bak aggregate