package com.application.auction.service;

import com.application.auction.dao.AccountDAO;
import com.application.auction.dao.AuctionDAO;
import com.application.auction.exceptions.ResourceNotFoundException;
import com.application.auction.model.account.Account;
import com.application.auction.model.auction.Auction;
import com.application.auction.model.auction.AuctionDTO;
import com.application.auction.model.auction.AuctionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionService {
    private final AuctionDAO auctionDAO;
    private final AccountDAO accountDAO;

    @Autowired
    public AuctionService(AuctionDAO auctionDAO, AccountDAO accountDAO) {
        this.auctionDAO = auctionDAO;
        this.accountDAO = accountDAO;
    }

    public List<AuctionDTO> getAllAuctions() {
        List<Auction> auctions = (List<Auction>) auctionDAO.findAll();
        return auctions.stream().map(AuctionMapper::toDTO).toList();
    }

    public AuctionDTO getAuctionById(Long id) {
        Auction auction = auctionDAO.findById(id).orElse(null);
        if (auction == null) {
            throw new ResourceNotFoundException("Auction with id " + id + " not found");
        }
        return AuctionMapper.toDTO(auction);
    }

    public void saveAuction(AuctionDTO auction) {
        Account account = accountDAO.findById(auction.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account with id " + auction.getOwnerId() + " not found"));
        Auction newAuction = AuctionMapper.toEntity(auction, account);
        auctionDAO.save(newAuction);
    }
}
