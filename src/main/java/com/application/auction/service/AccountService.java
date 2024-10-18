package com.application.auction.service;

import com.application.auction.dao.AccountDAO;
import com.application.auction.exceptions.ResourceNotFoundException;
import com.application.auction.model.account.Account;
import com.application.auction.model.account.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountDAO accountDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public AccountDTO getAccount(Long accountId) {
        Account account = accountDAO.findById(accountId).orElse(null);
        return account != null ? new AccountDTO(account) : null;
    }

    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = (List<Account>) accountDAO.findAll();
        return accounts.stream().map(AccountDTO::new).toList();
    }

    public void updateAccount(Long id, AccountDTO accountDTO) {
        Account account = accountDAO.findById(id).orElse(null);
        if (account == null) {
            throw new ResourceNotFoundException("Account with id " + id + " not found");
        }
        account.setName(accountDTO.getName());
        account.setEmail(accountDTO.getEmail());
        account.setRole(accountDTO.getRole());
        accountDAO.save(account);
    }

    public void deleteAccount(Long id) {
        Account account = accountDAO.findById(id).orElse(null);
        if (account == null) {
            throw new ResourceNotFoundException("Account with id " + id + " not found");
        }
        accountDAO.delete(account);
    }
}
