package com.dmprogramming.bankapp.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmprogramming.bankapp.entity.Account;
import com.dmprogramming.bankapp.repo.AccountRespo;
@Service
public class AccountServiceImpl implements AccountService{

	
	@Autowired
	AccountRespo repo;
	
	@Override
	public Account createAccount(Account account) {
		Account account_save = repo.save(account);
		return account_save;
	}

	@Override
	public Account getAccountDetailByAcno(Long accountNumber) {
		Optional<Account> account = repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account number is not present in bank system");
		}
		Account foundAcById= account.get();
		return foundAcById;
	}

	@Override
	public List<Account> getAllAccountDetail() {
		List<Account> lstAc = repo.findAll();
		return lstAc;
	}

	@Override
	public Account depositAmount(Long accountNumber, Double amount) {
		Optional<Account> account = repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account number is not present in bank system");
		}
		Account accountPresent = account.get();
		Double totalAmount = accountPresent.getAccount_balance()+amount;
		accountPresent.setAccount_balance(totalAmount);
		repo.save(accountPresent);
		return accountPresent;
	}

	@Override
	public Account withdrawAmount(Long accountNumber, Double amount) {
		
		Account foundAccount = getAccountDetailByAcno(accountNumber);
		Double foundAmountbalance = foundAccount.getAccount_balance();
		if(foundAmountbalance<amount) {
			throw new RuntimeException("Account balance is Low :" + foundAmountbalance);
		}
		Double balanceAmount = foundAmountbalance-amount;
		foundAccount.setAccount_balance(balanceAmount);
		repo.save(foundAccount);
		return foundAccount;
	}

	@Override
    public Account closeAccount(Long accountNumber) {
        Optional<Account> account = repo.findById(accountNumber);
        if (account.isEmpty()) {
            throw new RuntimeException("Account number is not present in bank system");
        }
        Account accountToDelete = account.get();
        repo.deleteById(accountNumber);
        return accountToDelete;
    }
}
