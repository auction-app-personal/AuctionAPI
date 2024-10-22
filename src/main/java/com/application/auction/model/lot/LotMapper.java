package com.application.auction.model.lot;

public class LotMapper {
    public static LotDto toDto(Lot lot) {
        LotDto lotDto = new LotDto();
        lotDto.setId(lot.getId());
        lotDto.setName(lot.getName());
        lotDto.setDescription(lot.getDescription());
        lotDto.setStartPrice(lot.getStartPrice());
        lotDto.setStatus(lot.getStatus());
        lotDto.setAuctionId(lot.getAuction().getId());
        return lotDto;
    }

    public static Lot toEntity(LotDto lotDto) {
        Lot lot = new Lot();
        lot.setId(lotDto.getId());
        lot.setName(lotDto.getName());
        lot.setDescription(lotDto.getDescription());
        lot.setStartPrice(lotDto.getStartPrice());
        lot.setStatus(lotDto.getStatus());
        return lot;
    }
}
