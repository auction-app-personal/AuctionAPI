package com.application.auction.service;

import com.application.auction.dao.AccountRepository;
import com.application.auction.dao.AuctionRepository;
import com.application.auction.exceptions.ResourceNotFoundException;
import com.application.auction.model.account.Account;
import com.application.auction.model.auction.Auction;
import com.application.auction.model.auction.AuctionDto;
import com.application.auction.model.auction.AuctionMapper;
import com.application.auction.model.lot.Lot;
import com.application.auction.model.lot.LotDto;
import com.application.auction.model.lot.LotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionService {
    private final AuctionRepository auctionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public AuctionService(AuctionRepository auctionRepository, AccountRepository accountRepository) {
        this.auctionRepository = auctionRepository;
        this.accountRepository = accountRepository;
    }

    public List<AuctionDto> getAllAuctions() {
        List<Auction> auctions = (List<Auction>) auctionRepository.findAll();
        return auctions.stream().map(AuctionMapper::toDto).toList();
    }

    public AuctionDto getAuctionDataById(Long id) {
        return AuctionMapper.toDto(getAuctionById(id));
    }

    public void saveAuction(AuctionDto auction) {
        Account account = getAccountById(auction.getOwnerId());
        Auction newAuction = AuctionMapper.toEntity(auction);
        newAuction.setAccount(account);
        auctionRepository.save(newAuction);
    }

    public void updateAuction(AuctionDto auction, Long id) {
        Account account = getAccountById(auction.getOwnerId());
        Auction oldAuction = getAuctionById(id);
        oldAuction.setName(auction.getName());
        oldAuction.setDescription(auction.getDescription());
        oldAuction.setStatus(auction.getStatus());
        oldAuction.setStartTimestamp(auction.getStartTimestamp());
        oldAuction.setDuration(auction.getDuration());
        oldAuction.setAccount(account);
        auctionRepository.save(oldAuction);
    }

    public void deleteAuction(Long id) {
        Auction auction = getAuctionById(id);
        auctionRepository.delete(auction);
    }

    public List<LotDto> getLotsByAuctionId(Long id) {
        Auction auction = getAuctionById(id);
        if (auction.getLotList() == null)
            throw new ResourceNotFoundException("Auction with id " + id + " does not have a lot list");
        return auction.getLotList().stream().map(LotMapper::toDto).toList();
    }

    public void addLot(Long id, LotDto lot) {
        Auction auction = getAuctionById(id);
        Lot newLot = LotMapper.toEntity(lot);
        newLot.setAuction(auction);
        auction.getLotList().add(newLot);
        auctionRepository.save(auction);
    }

    private Auction getAuctionById(Long id) {
        return auctionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Auction with id " + id + " not found"));
    }

    private Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account with id " + id + " not found"));
    }
}
