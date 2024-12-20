package com.application.auction.dao;

import com.application.auction.model.lot.Lot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotRepository extends CrudRepository<Lot, Long> {
}
