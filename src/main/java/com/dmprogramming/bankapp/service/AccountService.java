package com.dmprogramming.bankapp.service;

import java.util.List;

import com.dmprogramming.bankapp.entity.Account;

public interface AccountService {


	public Account createAccount(Account account );

	public Account getAccountDetailByAcno(Long accountNumber );
 
	public List<Account> getAllAccountDetail();
 
	public Account depositAmount(Long accountNumber , Double Amount);

	public Account withdrawAmount(Long accountNumber , Double Amount);
	public Account closeAccount(Long accountNumber);
	
}
