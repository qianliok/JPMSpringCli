package jpm.commands.databeans;

import static org.junit.Assert.*;
import jpm.commands.util.ProfitFormulaCtrl;
import jpm.commands.util.ProfitFormulaCtrl.TickerType;

import org.junit.Test;

public class TickerPriceDataTest {

	@Test
	public void testTickerPriceData() {
		TickerType type = TickerType.Equity;
		String name = "EEE";
		long quantity = 9;
		double buyPrice = 1.05;
		double sellPrice = 1.09;

		TickerPriceData tData = new TickerPriceData.TickerPriceBuilder(type, name, quantity).buyPrice(buyPrice)
				.sellPrice(sellPrice).build();

		assertEquals(tData.getType(), type);
		assertEquals(tData.getName(), name);
		assertEquals(tData.getQuantity(), quantity);
		assertEquals(ProfitFormulaCtrl.decFormatter.format(tData.getBuyPrice()),
				ProfitFormulaCtrl.decFormatter.format(buyPrice));
		assertEquals(ProfitFormulaCtrl.decFormatter.format(tData.getSellPrice()),
				ProfitFormulaCtrl.decFormatter.format(sellPrice));

	}

	@Test
	public void testGetBaseData() {
		TickerType type = TickerType.Equity;
		String name = "EEE";
		long quantity = 129;
		double buyPrice = 1.05;
		double sellPrice = -1.09;

		TickerPriceData tData = new TickerPriceData.TickerPriceBuilder(type, name, quantity).buyPrice(buyPrice)
				.sellPrice(sellPrice).build();
		
		TickerBaseData bData = tData.getBaseData();
		assertEquals(bData.getType(), type);
		assertEquals(bData.getName(), name);
		assertEquals(bData.getQuantity(), quantity);
		
	}

	@Test
	public void testGetBuyPrice() {
		TickerType type = TickerType.Equity;
		String name = "EEE";
		long quantity = 129;
		double buyPrice = 121.05;
		double sellPrice = -1.09;

		TickerPriceData tData = new TickerPriceData.TickerPriceBuilder(type, name, quantity).buyPrice(buyPrice)
				.sellPrice(sellPrice).build();
		
		assertEquals(ProfitFormulaCtrl.decFormatter.format(tData.getBuyPrice()),
				ProfitFormulaCtrl.decFormatter.format(buyPrice));
	}

	@Test
	public void testSetBuyPrice() {
		TickerType type = TickerType.Equity;
		String name = "EEE";
		long quantity = 129;
		double buyPrice = 121.05;
		double sellPrice = -1.09;

		TickerPriceData tData = new TickerPriceData.TickerPriceBuilder(type, name, quantity).buyPrice(buyPrice)
				.sellPrice(sellPrice).build();
		
		buyPrice = 12145.99;
		tData.setBuyPrice(buyPrice);
		assertEquals(ProfitFormulaCtrl.decFormatter.format(tData.getBuyPrice()),
				ProfitFormulaCtrl.decFormatter.format(buyPrice));
	}

	@Test
	public void testGetSellPrice() {
		TickerType type = TickerType.Equity;
		String name = "EEE";
		long quantity = 129;
		double buyPrice = 121.05;
		double sellPrice = -1.09;

		TickerPriceData tData = new TickerPriceData.TickerPriceBuilder(type, name, quantity).buyPrice(buyPrice)
				.sellPrice(sellPrice).build();
		
		assertEquals(ProfitFormulaCtrl.decFormatter.format(tData.getSellPrice()),
				ProfitFormulaCtrl.decFormatter.format(sellPrice));
	}

	@Test
	public void testSetSellPrice() {
		TickerType type = TickerType.Equity;
		String name = "EEE";
		long quantity = 129;
		double buyPrice = 121.05;
		double sellPrice = -1.09;

		TickerPriceData tData = new TickerPriceData.TickerPriceBuilder(type, name, quantity).buyPrice(buyPrice)
				.sellPrice(sellPrice).build();
		
		sellPrice = 0.02;
		tData.setSellPrice(sellPrice);
		assertEquals(ProfitFormulaCtrl.decFormatter.format(tData.getSellPrice()),
				ProfitFormulaCtrl.decFormatter.format(sellPrice));
	}

	@Test
	public void testGetCoupon() {
		TickerType type = TickerType.Bond;
		String name = "JJJ";
		long quantity = 129;
		double coupon = 0.06;

		TickerPriceData tData = new TickerPriceData.TickerPriceBuilder(type, name, quantity).coupon(coupon).build();
		assertEquals(ProfitFormulaCtrl.decFormatter.format(tData.getCoupon()),
				ProfitFormulaCtrl.decFormatter.format(coupon));
	}

	@Test
	public void testSetCoupon() {
		TickerType type = TickerType.Bond;
		String name = "JJJ";
		long quantity = 129;
		double coupon = 0.06;

		TickerPriceData tData = new TickerPriceData.TickerPriceBuilder(type, name, quantity).coupon(coupon).build();
		
		coupon = 0.02;
		tData.setCoupon(coupon);
		assertEquals(ProfitFormulaCtrl.decFormatter.format(tData.getCoupon()),
				ProfitFormulaCtrl.decFormatter.format(coupon));
	}

}
