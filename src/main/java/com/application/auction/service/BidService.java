package com.application.auction.service;

import com.application.auction.dao.AccountRepository;
import com.application.auction.dao.BidRepository;
import com.application.auction.dao.LotRepository;
import com.application.auction.exceptions.ResourceNotFoundException;
import com.application.auction.model.account.Account;
import com.application.auction.model.bid.Bid;
import com.application.auction.model.bid.BidDto;
import com.application.auction.model.bid.BidMapper;
import com.application.auction.model.lot.Lot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidService {
    private final BidRepository bidRepository;
    private final LotRepository lotRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public BidService(BidRepository bidRepository, LotRepository lotRepository, AccountRepository accountRepository) {
        this.bidRepository = bidRepository;
        this.lotRepository = lotRepository;
        this.accountRepository = accountRepository;
    }

    public List<BidDto> getAllBids() {
        List<Bid> bids = (List<Bid>) bidRepository.findAll();
        return bids.stream().map(BidMapper::toDto).toList();
    }

    public BidDto getBidDataById(Long id) {
        Bid bid = getBidById(id);
        return BidMapper.toDto(bid);
    }

    public void saveBid(BidDto bidDto) {
        Bid bid = BidMapper.toEntity(bidDto);
        Account account = getAccountByID(bidDto.getAccountId());
        Lot lot = getLotByID(bidDto.getLotId());
        bid.setAccount(account);
        bid.setLot(lot);
        bidRepository.save(bid);
    }

    public void updateBid(Long id, BidDto bidDto) {
        Bid bid = getBidById(id);
        Account account = getAccountByID(bidDto.getAccountId());
        Lot lot = getLotByID(bidDto.getLotId());
        bid.setAmount(bidDto.getAmount());
        bid.setTimeCreated(bidDto.getTimeCreated());
        bid.setAccount(account);
        bid.setLot(lot);
        bidRepository.save(bid);
    }

    public void deleteBid(Long id) {
        Bid bid = getBidById(id);
        bidRepository.delete(bid);
    }

    private Bid getBidById(Long id) {
        return bidRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bid with id " + id + " not found"));
    }

    private Lot getLotByID(Long id) {
        return lotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lot with id " + id + " not found"));
    }

    private Account getAccountByID(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account with id " + id + " not found"));
    }
}
