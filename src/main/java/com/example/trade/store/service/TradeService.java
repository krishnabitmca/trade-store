package com.example.trade.store.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trade.store.exception.InvalidMaturityDateException;
import com.example.trade.store.exception.LowerVersionException;
import com.example.trade.store.exception.NullTradeObjectException;
import com.example.trade.store.model.Trade;
import com.example.trade.store.repository.TradeStoreReporsitory;

/**
 * Define the business logic in this class
 * 
 * @author user
 *
 */
@Service
public class TradeService {

	@Autowired
	TradeStoreReporsitory tradeStoreReporsitory;

	// getting all student records
	public List<Trade> getAllTrades() {
		List<Trade> trades = new ArrayList<Trade>();
		tradeStoreReporsitory.findAll().forEach(trade -> trades.add(trade));
		return trades;
	}

	// getting a specific record
	public Trade getTradeById(int id) {
		return tradeStoreReporsitory.findById(id).get();
	}

	/**
	 * It saves trade object in database. Also validates as per business rules.
	 * 
	 * @param trade
	 * @throws Exception
	 */
	public boolean saveOrUpdate(Trade trade) throws Exception {

		if (trade == null) {
			throw new NullTradeObjectException();
		}

		if (tradeStoreReporsitory.existsById(trade.getId())) {
			Trade tradeById = getTradeById(trade.getId());
			if (tradeById.getVersion() >= trade.getVersion()) {

				throw new LowerVersionException(trade.getId());

			}
		}
		Date maturityDate = trade.getMaturityDate();

		Date date = new Date();

		if (maturityDate.compareTo(date) < 0) {
			throw new InvalidMaturityDateException(trade.getId());
		}

		trade.setCreatedDate(date);
		trade.setExpired(false);
		tradeStoreReporsitory.save(trade);
		return true;
	}

	// deleting a specific record
	public void delete(int id) {
		tradeStoreReporsitory.deleteById(id);
	}

	public void updateExpiryDate() {

		List<Trade> trades = new ArrayList<Trade>();

		tradeStoreReporsitory.findAll().forEach(trade -> trades.add(trade));

		for (Trade trade2 : trades) {
			if (trade2.getMaturityDate().compareTo(new Date()) < 0) {
				trade2.setExpired(true);
			}

		}
		tradeStoreReporsitory.saveAll(trades);

	}
}
