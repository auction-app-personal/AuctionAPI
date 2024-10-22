package com.application.auction.controller;

import com.application.auction.model.bid.BidDto;
import com.application.auction.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bids")
public class BidController {
    private final BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @GetMapping()
    public ResponseEntity<List<BidDto>> getBids() {
        List<BidDto> bids = bidService.getAllBids();
        return new ResponseEntity<>(bids, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BidDto> getBidById(@PathVariable Long id) {
        BidDto bid = bidService.getBidDataById(id);
        return new ResponseEntity<>(bid, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> createBid(@RequestBody BidDto bidDto) {
        bidService.saveBid(bidDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBid(@PathVariable Long id, @RequestBody BidDto bidDto) {
        bidService.updateBid(id, bidDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBid(@PathVariable Long id) {
        bidService.deleteBid(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
