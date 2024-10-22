package com.application.auction.model.auction;

public class AuctionMapper {
    public static AuctionDto toDto(Auction auction) {
        AuctionDto auctionDTO = new AuctionDto();
        auctionDTO.setId(auction.getId());
        auctionDTO.setName(auction.getName());
        auctionDTO.setDescription(auction.getDescription());
        auctionDTO.setStartTimestamp(auction.getStartTimestamp());
        auctionDTO.setDuration(auction.getDuration());
        auctionDTO.setStatus(auction.getStatus());
        auctionDTO.setOwnerId(auction.getAccount().getId());
        return auctionDTO;
    }

    public static Auction toEntity(AuctionDto auctionDto) {
        Auction auction = new Auction();
        auction.setId(auctionDto.getId());
        auction.setName(auctionDto.getName());
        auction.setDescription(auctionDto.getDescription());
        auction.setStartTimestamp(auctionDto.getStartTimestamp());
        auction.setDuration(auctionDto.getDuration());
        auction.setStatus(auctionDto.getStatus());
        return auction;
    }
}
