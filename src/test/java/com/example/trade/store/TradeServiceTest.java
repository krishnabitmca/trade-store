package com.example.trade.store;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.trade.store.model.Trade;
import com.example.trade.store.service.TradeService;

@RunWith(SpringJUnit4ClassRunner.class)
public class TradeServiceTest {

	@Autowired
	TradeService tradeService;

	@Test
	public void testTradeService() {
		assertEquals("class com.example.trade.store.service.TradeService", this.tradeService.getClass().toString());
	}

	@Test
	public void testsaveOrUpdate() {

		Trade trade = new Trade();
		trade.setId(100);
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

}
