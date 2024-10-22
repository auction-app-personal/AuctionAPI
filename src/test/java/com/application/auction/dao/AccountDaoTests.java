package com.application.auction.dao;

import com.application.auction.model.account.Account;
import com.application.auction.model.account.AccountRole;
import com.application.auction.model.auction.Auction;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Sql(scripts = "classpath:/database/general/reset.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "classpath:/database/general/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AccountDaoTests {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountDaoTests(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Test
    public void testAccountSave() {
        Account account = getSampleAccount();
        Long id = accountRepository.save(account).getId();
        Account savedAccount = accountRepository.findById(id).orElse(null);
        assert savedAccount != null;
        assert savedAccount.getId().equals(id);
        assert savedAccount.getRole() == AccountRole.ADMIN;
        assert savedAccount.getEmail().equals("test@test.com");
        assert savedAccount.getPassword().equals("abc");
        assert savedAccount.getName().equals("Test");
    }

    @Test
    public void testAccountUpdate() {
        Account account = getSampleAccount();
        Long id = accountRepository.save(account).getId();
        Account newAccount = getSampleAccount();
        newAccount.setId(id);
        newAccount.setRole(AccountRole.USER);
        newAccount.setEmail("test-update@test.com");
        accountRepository.save(newAccount);
        Account updatedAcc = accountRepository.findById(id).orElse(null);
        assert updatedAcc != null;
        assert updatedAcc.getId().equals(id);
        assert updatedAcc.getRole() == AccountRole.USER;
        assert updatedAcc.getEmail().equals("test-update@test.com");
        assert updatedAcc.getPassword().equals(account.getPassword());
        assert updatedAcc.getName().equals(account.getName());
    }

    @Test
    public void testAccountDelete() {
        Account account = getSampleAccount();
        Long id = accountRepository.save(account).getId();
        assert accountRepository.findById(id).isPresent();
        accountRepository.delete(account);
        assert accountRepository.findById(id).isEmpty();
    }

    @Test
    @Transactional
    public void testAccountCreateWithAuctionsCascade(){
        Account account = getSampleAccount();
        List<Auction> samples = getSampleAuctions();
        account.setAuctionsOwnedByAccount(samples);
        Long id = accountRepository.save(account).getId();
        Account savedAccount = accountRepository.findById(id).orElse(null);
        assert savedAccount != null;
        List<Auction> retrievedAuctions = savedAccount.getAuctionsOwnedByAccount();
        assert samples.size() == retrievedAuctions.size();
        for (int i = 0; i < getSampleAuctions().size(); i++) {
            assert retrievedAuctions.get(i).equals(samples.get(i));
        }
    }

    private Account getSampleAccount() {
        Account account = new Account();
        account.setName("Test");
        account.setRole(AccountRole.ADMIN);
        account.setEmail("test@test.com");
        account.setPassword("abc");
        return account;
    }

    private List<Auction> getSampleAuctions(){
        List<Auction> auctions = new ArrayList<>();
        auctions.add(new Auction("test1"));
        auctions.add(new Auction("test2"));
        auctions.add(new Auction("test3"));
        auctions.add(new Auction("test4"));
        return auctions;
    }
}
