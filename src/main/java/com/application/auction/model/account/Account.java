package com.application.auction.model.account;

import com.application.auction.model.bid.Bid;
import com.application.auction.model.auction.Auction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private AccountRole role;

    @OneToMany(mappedBy = "account")
    private List<Bid> bids;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Auction> auctionsOwnedByAccount;

    @ManyToMany
    @JoinTable(
            name = "account_auction",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "auction_id")
    )
    private List<Auction> auctionsTakePart;


    public Account(String name, String email, String password, AccountRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
