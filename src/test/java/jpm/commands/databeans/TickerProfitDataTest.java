package jpm.commands.databeans;

import static org.junit.Assert.*;
import jpm.commands.util.ProfitFormulaCtrl;
import jpm.commands.util.ProfitFormulaCtrl.TickerType;

import org.junit.Test;

public class TickerProfitDataTest {

	@Test
	public void testTickerProfitData() {
		TickerType type = TickerType.Bond;
		String name = "EEE";
		long quantity = 9;

		TickerBaseData tData = new TickerBaseData(type, name, quantity);
		TickerProfitData pData = new TickerProfitData(tData);
		assertEquals(pData.getType(), type);
		assertEquals(pData.getName(), name);
		assertEquals(pData.getQuantity(), quantity);
	}

	@Test
	public void testGetProfit() {
		TickerType type = TickerType.Bond;
		String name = "EEE";
		long quantity = 9;
		double profit = 0.12;
		
		TickerBaseData tData = new TickerBaseData(type, name, quantity);
		TickerProfitData pData = new TickerProfitData(tData);
		pData.setProfit(profit);
		assertEquals(ProfitFormulaCtrl.decFormatter.format(pData.getProfit()), ProfitFormulaCtrl.decFormatter.format(profit));
	}

	@Test
	public void testSetProfit() {
		TickerType type = TickerType.Equity;
		String name = "HHH";
		long quantity = 129;
		double profit = 3.12;
		
		TickerBaseData tData = new TickerBaseData(type, name, quantity);
		TickerProfitData pData = new TickerProfitData(tData);
		pData.setProfit(profit);
		assertEquals(ProfitFormulaCtrl.decFormatter.format(pData.getProfit()), ProfitFormulaCtrl.decFormatter.format(profit));
	}

	@Test
	public void testToString() {
		TickerType type = TickerType.Equity;
		String name = "HHH";
		long quantity = 129;
		double profit = 3.12;
		
		TickerBaseData tData = new TickerBaseData(type, name, quantity);
		TickerProfitData pData = new TickerProfitData(tData);
		pData.setProfit(profit);
		assertEquals(pData.toString(), "Equity,HHH,129,3.12");	
	}

}
