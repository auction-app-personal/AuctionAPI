package com.application.auction.model.bid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BidDto {
    private Long id;
    private double amount;
    private LocalDateTime timeCreated;
    private Long lotId;
    private Long accountId;
}
