package com.application.auction.service;

import com.application.auction.dao.AccountRepository;
import com.application.auction.dao.AuctionRepository;
import com.application.auction.dao.LotRepository;
import com.application.auction.exceptions.ResourceNotFoundException;
import com.application.auction.model.account.Account;
import com.application.auction.model.auction.Auction;
import com.application.auction.model.bid.Bid;
import com.application.auction.model.bid.BidDto;
import com.application.auction.model.bid.BidMapper;
import com.application.auction.model.lot.Lot;
import com.application.auction.model.lot.LotDto;
import com.application.auction.model.lot.LotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotService {
    private final LotRepository lotRepository;
    private final AuctionRepository auctionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public LotService(LotRepository lotRepository, AuctionRepository auctionRepository, AccountRepository accountRepository) {
        this.lotRepository = lotRepository;
        this.auctionRepository = auctionRepository;
        this.accountRepository = accountRepository;
    }

    public List<LotDto> getAllLots() {
        List<Lot> lots = (List<Lot>) lotRepository.findAll();
        return lots.stream().map(LotMapper::toDto).toList();
    }

    public LotDto getLotDataById(Long id) {
        Lot lot = getLotById(id);
        return LotMapper.toDto(lot);
    }

    public void saveLot(LotDto lot) {
        Auction auction = getAuctionById(lot.getAuctionId());
        Lot newLot = LotMapper.toEntity(lot);
        newLot.setAuction(auction);
        lotRepository.save(newLot);
    }

    public void updateLot(Long id, LotDto lot) {
        Auction auction = getAuctionById(lot.getAuctionId());
        Lot newLot = getLotById(id);
        newLot.setName(lot.getName());
        newLot.setDescription(lot.getDescription());
        newLot.setStatus(lot.getStatus());
        newLot.setStartPrice(lot.getStartPrice());
        newLot.setAuction(auction);
        lotRepository.save(newLot);
    }

    public void deleteLot(Long id) {
        Lot lot = getLotById(id);
        lotRepository.delete(lot);
    }

    public void placeBid(Long lotId, BidDto bidDto) {
        Lot lot = getLotById(lotId);
        List<Bid> bids = lot.getBids();
        Account account = getAccountById(bidDto.getAccountId());
        if (bids == null)
            throw new ResourceNotFoundException("Lot with id " + lotId + " does not have bid list.");
        Bid newBid = BidMapper.toEntity(bidDto);
        newBid.setLot(lot);
        newBid.setAccount(account);
        bids.add(newBid);
        lotRepository.save(lot);
    }

    private Auction getAuctionById(Long id) {
        return auctionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Auction with id " + id + " not found"));
    }

    private Lot getLotById(Long id) {
        return lotRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Lot with id " + id + " not found"));
    }

    private Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account with id " + id + " not found"));
    }
}
