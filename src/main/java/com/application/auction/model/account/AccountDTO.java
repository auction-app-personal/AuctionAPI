package com.application.auction.model.account;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id;
    private String name;
    private String email;
    private AccountRole role;

    public AccountDTO(Account account) {
        id = account.getId();
        name = account.getName();
        email = account.getEmail();
        role = account.getRole();
    }
}
