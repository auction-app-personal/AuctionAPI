package com.application.auction.model.lot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LotDto {
    private Long id;
    private String name;
    private String description;
    private double startPrice;
    private LotStatus status;
    private Long auctionId;
}
