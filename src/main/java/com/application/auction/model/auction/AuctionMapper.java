package com.application.auction.model.auction;

import com.application.auction.model.account.Account;

public class AuctionMapper {
    public static AuctionDTO toDTO(Auction auction) {
        AuctionDTO auctionDTO = new AuctionDTO();
        auctionDTO.setId(auction.getId());
        auctionDTO.setName(auction.getName());
        auctionDTO.setDescription(auction.getDescription());
        auctionDTO.setDuration(auction.getDuration());
        auctionDTO.setStatus(auction.getStatus());
        auctionDTO.setOwnerId(auction.getAccount().getId());
        return auctionDTO;
    }

    public static Auction toEntity(AuctionDTO auctionDTO, Account account) {
        Auction auction = new Auction();
        auction.setId(auctionDTO.getId());
        auction.setName(auctionDTO.getName());
        auction.setDescription(auctionDTO.getDescription());
        auction.setDuration(auctionDTO.getDuration());
        auction.setStatus(auctionDTO.getStatus());
        auction.setAccount(account);
        return auction;
    }
}
