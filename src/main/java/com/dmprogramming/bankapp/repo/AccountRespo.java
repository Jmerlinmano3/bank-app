package com.dmprogramming.bankapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dmprogramming.bankapp.entity.Account;

@Repository
public interface AccountRespo extends JpaRepository<Account, Long> {

}
