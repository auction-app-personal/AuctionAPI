package com.application.auction.controller;

import com.application.auction.model.bid.BidDto;
import com.application.auction.model.lot.LotDto;
import com.application.auction.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lots")
public class LotController {
    private final LotService lotService;

    @Autowired
    public LotController(LotService lotService) {
        this.lotService = lotService;
    }

    @GetMapping()
    public ResponseEntity<List<LotDto>> getAllLots() {
        List<LotDto> lots = lotService.getAllLots();
        return new ResponseEntity<>(lots, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotDto> getLotById(@PathVariable Long id) {
        LotDto lot = lotService.getLotDataById(id);
        return new ResponseEntity<>(lot, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> createLot(@RequestBody LotDto lot) {
        lotService.saveLot(lot);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLot(@PathVariable Long id, @RequestBody LotDto lot) {
        lotService.updateLot(id, lot);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLot(@PathVariable Long id) {
        lotService.deleteLot(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/bid")
    public ResponseEntity<Void> placeBid(@PathVariable Long id, @RequestBody BidDto bid) {
        lotService.placeBid(id, bid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
