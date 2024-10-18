package com.application.auction.service;

import com.application.auction.dao.AccountDAO;
import com.application.auction.model.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountDAO accountDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account getAccount(Long accountId) {
        return accountDAO.findById(accountId).orElse(null);
    }
}
