package com.application.auction.model.auction;

import com.application.auction.model.account.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuctionDTO {
    private Long id;
    private String name;
    private String description;
    private Duration duration = Duration.ofMinutes(0);
    private AuctionStatus status = AuctionStatus.CREATED;
    private Long ownerId;
}
