package com.application.auction.model.auction;

import com.application.auction.model.account.Account;
import com.application.auction.model.lot.Lot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "auction")
@Getter
@Setter
@NoArgsConstructor
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "duration", columnDefinition = "interval")
    private Duration duration = Duration.ofMinutes(0);

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AuctionStatus status = AuctionStatus.CREATED;

    @Column(name = "start_timestamp")
    private LocalDateTime startTimestamp;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private List<Lot> lotList;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    @ManyToMany
    @JoinTable(
            name = "account_auction",
            joinColumns = @JoinColumn(name = "auction_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private List<Account> accounts;

    public Auction(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return Objects.equals(name, auction.name) && Objects.equals(description, auction.description) && Objects.equals(duration, auction.duration) && status == auction.status && Objects.equals(startTimestamp, auction.startTimestamp) && Objects.equals(account, auction.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, duration, status, startTimestamp, account);
    }
}
