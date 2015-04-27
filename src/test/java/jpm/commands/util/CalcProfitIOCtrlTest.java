package jpm.commands.util;

import static org.junit.Assert.*;
import jpm.commands.databeans.TickerProfitData;
import jpm.commands.exception.InvalidLineDataFormatException;

import org.junit.Test;

public class CalcProfitIOCtrlTest {


	@Test
	public void testProcessLine() {
		CalcProfitIOCtrl calcProfitIoCtrl = new CalcProfitIOCtrl();

		try {
			String line = "Equity,AAA,123,1.01,1.10";
			TickerProfitData pData = calcProfitIoCtrl.processLine(line);
			assertEquals(ProfitFormulaCtrl.decFormatter.format(pData.getProfit()), ProfitFormulaCtrl.decFormatter.format(11.07));
			
			
			line = "Bond,DD,9,,,1.29";
			pData = calcProfitIoCtrl.processLine(line);
			assertEquals(ProfitFormulaCtrl.decFormatter.format(pData.getProfit()), ProfitFormulaCtrl.decFormatter.format(11.61));
			
			
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		} catch (InvalidLineDataFormatException ile) {
			ile.printStackTrace();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		}

	}

}
