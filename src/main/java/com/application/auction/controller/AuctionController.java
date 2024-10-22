package com.application.auction.controller;

import com.application.auction.model.auction.AuctionDto;
import com.application.auction.model.lot.LotDto;
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
    public ResponseEntity<List<AuctionDto>> getAllAuctions() {
        List<AuctionDto> auctions = auctionService.getAllAuctions();
        return new ResponseEntity<>(auctions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuctionDto> getAuctionById(@PathVariable Long id) {
        AuctionDto auction = auctionService.getAuctionDataById(id);
        return new ResponseEntity<>(auction, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createAuction(@RequestBody AuctionDto auction) {
        auctionService.saveAuction(auction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAuction(@PathVariable Long id, @RequestBody AuctionDto auction) {
        auctionService.updateAuction(auction, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuction(@PathVariable Long id) {
        auctionService.deleteAuction(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/lots")
    public ResponseEntity<List<LotDto>> getLotsPerAuction(@PathVariable Long id) {
        List<LotDto> lots = auctionService.getLotsByAuctionId(id);
        return new ResponseEntity<>(lots, HttpStatus.OK);
    }

    @PostMapping("/{id}/lots")
    public ResponseEntity<Void> addLot(@PathVariable Long id, @RequestBody LotDto lot) {
        auctionService.addLot(id, lot);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
