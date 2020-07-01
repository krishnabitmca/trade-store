package com.example.trade.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.trade.store.exception.InvalidMaturityDateException;
import com.example.trade.store.exception.LowerVersionException;
import com.example.trade.store.model.Trade;
import com.example.trade.store.repository.TradeStoreReporsitory;
import com.example.trade.store.service.TradeService;

@SpringBootTest
class TradeStoreApplicationTests {

	@Autowired
	TradeService tradeService;
	@Autowired
	TradeStoreReporsitory tradeStoreReporsitory;

	@Test
	public void testTradeService() {
		assertEquals("class com.example.trade.store.service.TradeService", this.tradeService.getClass().toString());
	}

	@Test
	public void testsaveOrUpdate() {

		Trade trade = new Trade();
		trade.setId(200);
		trade.setVersion(1);
		trade.setCpID("CP-1");
		trade.setBookID("B1");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 7); // +days
		trade.setMaturityDate(cal.getTime());
		try {
			assertEquals(Boolean.TRUE, tradeService.saveOrUpdate(trade));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLowerVersion() {

		Trade trade = new Trade();
		trade.setId(100);
		trade.setVersion(2);
		trade.setCpID("CP-1");
		trade.setBookID("B1");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 7); // +days
		trade.setMaturityDate(cal.getTime());

		Trade trade2 = new Trade();
		trade2.setId(100);
		trade2.setVersion(1);
		trade2.setCpID("CP-1");
		trade2.setBookID("B1");
		cal.add(Calendar.DATE, 7); // +days
		trade.setMaturityDate(cal.getTime());
		try {
			tradeService.saveOrUpdate(trade);
			tradeService.saveOrUpdate(trade2);
		} catch (Exception e) {
			assertTrue(e instanceof LowerVersionException);
		}
	}

	@Test
	public void testInvalidMaturityDate() {

		Trade trade = new Trade();
		trade.setId(300);
		trade.setVersion(1);
		trade.setCpID("CP-3");
		trade.setBookID("B1");
		trade.setMaturityDate(new Date());

		try {
			tradeService.saveOrUpdate(trade);
		} catch (Exception e) {
			assertTrue(e instanceof InvalidMaturityDateException);
		}
	}

	@Test
	public void testExpiryDate() {

		Trade trade = new Trade();
		trade.setId(400);
		trade.setVersion(1);
		trade.setCpID("CP-3");
		trade.setBookID("B1");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		trade.setMaturityDate(cal.getTime());
		
		tradeStoreReporsitory.save(trade);
		tradeService.updateExpiryDate();
		
		Trade tradeById = tradeService.getTradeById(trade.getId());
		assertEquals(Boolean.TRUE, tradeById.isExpired());
	}
}
