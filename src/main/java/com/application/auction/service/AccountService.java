package com.application.auction.service;

import com.application.auction.dao.AccountDAO;
import com.application.auction.exceptions.ResourceNotFoundException;
import com.application.auction.model.account.Account;
import com.application.auction.model.account.AccountDTO;
import com.application.auction.model.account.AccountMapper;
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

    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = (List<Account>) accountDAO.findAll();
        return accounts.stream().map(AccountMapper::toDTO).toList();
    }

    public AccountDTO getAccount(Long accountId) {
        Account account = accountDAO.findById(accountId).orElse(null);
        if (account == null) {
            throw new ResourceNotFoundException("Account with id " + accountId + " not found");
        }
        return AccountMapper.toDTO(account);
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
        if (!accountDAO.existsById(id)) {
            throw new ResourceNotFoundException("Account with id " + id + " not found");
        }
        accountDAO.deleteById(id);
    }
}
