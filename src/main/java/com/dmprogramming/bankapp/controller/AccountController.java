package com.dmprogramming.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dmprogramming.bankapp.entity.Account;
import com.dmprogramming.bankapp.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService service;
	
	@PostMapping("/createaccount")
	public ResponseEntity<Account> createAccount(@RequestBody Account account){
		Account createAccount= service.createAccount(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(createAccount);
				
	}
	
	@GetMapping("/{accountNumber}")
	public ResponseEntity<?> getAccountByAcNUmber(@PathVariable Long accountNumber) {
try {
		Account getAcDetailByAcno = service.getAccountDetailByAcno(accountNumber);
		return ResponseEntity.ok(getAcDetailByAcno);
	} catch (RuntimeException e) {
		return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
	}
	}
	

	@GetMapping("/getallaccounts")
	public List<Account> getAllAccountDetail(){
		List<Account> listAcc= service.getAllAccountDetail();
		return listAcc;
	}

	@PutMapping("/deposit/{accountNumber}/{amount}")
	public ResponseEntity<?> depositAccount(@PathVariable Long accountNumber,@PathVariable Double amount) {
		try{
			Account account = service.depositAmount(accountNumber, amount);
			return ResponseEntity.ok(account);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
	

	@PutMapping("/withdraw/{accountNumber}/{amount}")
	public ResponseEntity<?> withdrawAmount(@PathVariable Long accountNumber,@PathVariable Double amount){
		try {
			Account account = service.withdrawAmount(accountNumber, amount);
			return ResponseEntity.ok(account);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{accountNumber}")
	public ResponseEntity<String> closeAccount(@PathVariable Long accountNumber) {
		try {
			Account closedAccount = service.closeAccount(accountNumber);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account has been Closed AC# :" + closedAccount.getAccount_number());
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());	
		}
		
		
	}
	
}