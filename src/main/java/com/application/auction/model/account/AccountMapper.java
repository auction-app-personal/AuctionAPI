package com.application.auction.model.account;

public class AccountMapper {
    public static AccountDto toDto(Account account) {
        AccountDto accountDTO = new AccountDto();
        accountDTO.setId(account.getId());
        accountDTO.setName(account.getName());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setRole(account.getRole());
        return accountDTO;
    }

    public static Account toEntity(AccountDto accountDto) {
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setName(accountDto.getName());
        account.setEmail(accountDto.getEmail());
        account.setRole(accountDto.getRole());
        return account;
    }
}
