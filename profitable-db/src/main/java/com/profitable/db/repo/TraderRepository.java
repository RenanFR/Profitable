package com.profitable.db.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.profitable.model.Trader;

public interface TraderRepository extends MongoRepository<Trader, String> {
}
