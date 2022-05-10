package com.turkcell.elearner.application.features.accountTypes.commands;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turkcell.elearner.application.features.accountTypes.commands.create.AccountTypeCreatedEvent;
import com.turkcell.elearner.application.features.accountTypes.commands.delete.AccountTypeDeletedEvent;
import com.turkcell.elearner.application.features.accountTypes.commands.update.AccountTypeUpdatedEvent;
import com.turkcell.elearner.domain.AccountType;
import com.turkcell.elearner.persistence.AccountTypeRepository;

//managera benzer sadece tek bir commandin manageri
@Component
public class AccountTypeEventsHandler {

	private AccountTypeRepository accountTypeRepository;

	@Autowired
	public AccountTypeEventsHandler(AccountTypeRepository accountTypeRepository) {
		this.accountTypeRepository = accountTypeRepository;
	}
	
	@EventHandler
	public void create(AccountTypeCreatedEvent accountTypeCreatedEvent) {
		//business codu olabilir checkif
		AccountType accountType = new AccountType();
		BeanUtils.copyProperties(accountTypeCreatedEvent, accountType);
		this.accountTypeRepository.save(accountType);
	}

	@EventHandler
	public void delete(AccountTypeDeletedEvent accountTypeDeletedEvent) {
		
		//AccountType accountType = handleAccountType(accountTypeDeletedEvent.getAccountTypeId());
		
		AccountType accountType = accountTypeRepository.findByAccountName(accountTypeDeletedEvent.getAccountName());
		
		this.accountTypeRepository.delete(accountType);
	}
	
	@EventHandler
	public void update(AccountTypeUpdatedEvent accountTypeUpdatedEvent) {
		//AccountType accountType = handleAccountType(accountTypeUpdatedEvent.getAccountTypeId());
		
		AccountType accountType = accountTypeRepository.findByAccountName(accountTypeUpdatedEvent.getAccountName());
		
		accountType.setAccountName(accountTypeUpdatedEvent.getAccountName());
		accountType.setDescription(accountTypeUpdatedEvent.getDescription());
		accountType.setPrice(accountTypeUpdatedEvent.getPrice());
		
		this.accountTypeRepository.save(accountType);
	}
	
	@QueryHandler
	public AccountType handleAccountType(String accountTypeId) {
		return accountTypeRepository.getById(accountTypeId);
	}
	  
	


}
