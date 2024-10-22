package com.application.auction.service;

import com.application.auction.dao.AccountRepository;
import com.application.auction.exceptions.ResourceNotFoundException;
import com.application.auction.model.account.Account;
import com.application.auction.model.account.AccountDto;
import com.application.auction.model.account.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = (List<Account>) accountRepository.findAll();
        return accounts.stream().map(AccountMapper::toDto).toList();
    }

    public AccountDto getAccount(Long accountId) {
        Account account = getAccountById(accountId);
        return AccountMapper.toDto(account);
    }


    public void updateAccount(Long id, AccountDto accountDTO) {
        Account account = getAccountById(id);
        account.setName(accountDTO.getName());
        account.setEmail(accountDTO.getEmail());
        account.setRole(accountDTO.getRole());
        accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        Account account = getAccountById(id);
        accountRepository.delete(account);
    }

    private Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account with id " + id + " not found"));
    }
}
