package jpm.commands.util;

import static org.junit.Assert.assertEquals;
import jpm.commands.databeans.TickerPriceData;
import jpm.commands.util.ProfitFormulaCtrl;
import jpm.commands.util.ProfitFormulaCtrl.TickerType;

import org.junit.Test;

public class ProfitFormulaCtrlTest {

	@Test
	public void testCalcTickerData() {
		 TickerType type = TickerType.Equity;
		 String name = "EEE";
		long quantity = 9;
		double buyPrice = 1.05;
		double sellPrice = 1.09;
		
		TickerPriceData tData = new TickerPriceData.TickerPriceBuilder(type, name, quantity).buyPrice(buyPrice).sellPrice(sellPrice).build();
		double profit = ProfitFormulaCtrl.calcTickerData(tData);
		assertEquals(ProfitFormulaCtrl.decFormatter.format(profit), "0.36");
	}

	@Test
	public void testSimpleEquity() {
		long quantity = 3;
		double buyPrice = 1.05;
		double sellPrice = 1.01;
		double profit = ProfitFormulaCtrl.simpleEquity(quantity, buyPrice, sellPrice);
		assertEquals(ProfitFormulaCtrl.decFormatter.format(profit), "-0.12");
	}

	@Test
	public void testSimpleBond() {
		long quantity = 3;
		double coupon = 0.13;
		double profit = ProfitFormulaCtrl.simpleBond(quantity, coupon);
		assertEquals(ProfitFormulaCtrl.decFormatter.format(profit), "0.39");
	}

}
