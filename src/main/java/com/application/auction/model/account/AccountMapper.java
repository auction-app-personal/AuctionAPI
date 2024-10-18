package com.application.auction.model.account;

public class AccountMapper {
    public static AccountDTO toDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setName(account.getName());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setRole(account.getRole());
        return accountDTO;
    }

    public static Account toEntity(AccountDTO accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setName(accountDTO.getName());
        account.setEmail(accountDTO.getEmail());
        account.setRole(accountDTO.getRole());
        return account;
    }
}
