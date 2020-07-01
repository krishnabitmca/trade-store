package com.example.trade.store.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.trade.store.model.Trade;

public interface TradeStoreReporsitory extends CrudRepository<Trade, Integer> {

}
