package com.application.auction.model.bid;

public class BidMapper {
    public static BidDto toDto(Bid bid) {
        BidDto bidDto = new BidDto();
        bidDto.setId(bid.getId());
        bidDto.setAmount(bid.getAmount());
        bidDto.setTimeCreated(bid.getTimeCreated());
        bidDto.setAccountId(bid.getAccount().getId());
        bidDto.setLotId(bid.getLot().getId());
        return bidDto;
    }

    public static Bid toEntity(BidDto bidDto) {
        Bid bid = new Bid();
        bid.setId(bidDto.getId());
        bid.setAmount(bidDto.getAmount());
        bid.setTimeCreated(bidDto.getTimeCreated());
        return bid;
    }
}
