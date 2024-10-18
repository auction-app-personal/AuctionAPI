package com.application.auction.controller;

import com.application.auction.model.auction.AuctionDTO;
import com.application.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctions")
public class AuctionController {
    private final AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AuctionDTO>> getAllAuctions() {
        List<AuctionDTO> auctions = auctionService.getAllAuctions();
        return new ResponseEntity<>(auctions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuctionDTO> getAuctionById(@PathVariable Long id) {
        AuctionDTO auction = auctionService.getAuctionById(id);
        return new ResponseEntity<>(auction, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AuctionDTO> createAuction(@RequestBody AuctionDTO auction) {
        auctionService.saveAuction(auction);
        return new ResponseEntity<>(auction, HttpStatus.CREATED);
    }
}
