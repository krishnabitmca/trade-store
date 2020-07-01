package com.example.trade.store.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.trade.store.exception.InvalidMaturityDateException;
import com.example.trade.store.exception.LowerVersionException;
import com.example.trade.store.exception.NullTradeObjectException;
import com.example.trade.store.model.Trade;
import com.example.trade.store.service.TradeService;

@RestController
public class TradeStoreController {
	@Autowired
	TradeService tradeService;

	@GetMapping("/trades")
	private List<Trade> getAllTrade() {
		return tradeService.getAllTrades();
	}

	@GetMapping("/trade/{id}")
	private Trade getTrade(@PathVariable("id") int id) {
		return tradeService.getTradeById(id);
	}

	// creating a delete mapping that deletes a specific student
	@DeleteMapping("/trade/{id}")
	private void deleteTrade(@PathVariable("id") int id) {
		tradeService.delete(id);
	}

	// creating post mapping that post the student detail in the database
	@PostMapping("/trade")
	private ResponseEntity<Object> saveTrade(@RequestBody Trade trade) {
		try {
			tradeService.saveOrUpdate(trade);
		} catch (LowerVersionException e) {
			Map<String, Object> body = new LinkedHashMap<>();
			body.put("timestamp", LocalDateTime.now());
			body.put("message", "Lower Version Trade is submitted");
			return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
			
		}catch (InvalidMaturityDateException e) {
			Map<String, Object> body = new LinkedHashMap<>();
			body.put("timestamp", LocalDateTime.now());
			body.put("message", "Maturity date can not be earlier than current date");
			return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
			
		}catch (NullTradeObjectException e) {
			Map<String, Object> body = new LinkedHashMap<>();
			body.put("timestamp", LocalDateTime.now());
			body.put("message", "Trade object is null");
			return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
			
		}catch (Exception e) {
			Map<String, Object> body = new LinkedHashMap<>();
			body.put("timestamp", LocalDateTime.now());
			body.put("message", "Invalid state");

			return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(trade.getId(), HttpStatus.OK);
	}

}
