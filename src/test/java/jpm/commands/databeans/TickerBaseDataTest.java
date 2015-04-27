package jpm.commands.databeans;

import static org.junit.Assert.assertEquals;
import jpm.commands.util.ProfitFormulaCtrl.TickerType;

import org.junit.Test;

public class TickerBaseDataTest {

	@Test
	public void testTickerBaseDataTickerTypeStringLong() {
		TickerType type = TickerType.Equity;
		String name = "EEE";
		long quantity = 9;

		TickerBaseData tData = new TickerBaseData(type, name, quantity);

		assertEquals(tData.getType(), type);
		assertEquals(tData.getName(), name);
		assertEquals(tData.getQuantity(), quantity);

	}

	@Test
	public void testTickerBaseDataTickerBaseData() {
		TickerType type = TickerType.Equity;
		String name = "FFF";
		long quantity = 91111222;

		TickerBaseData tData = new TickerBaseData(type, name, quantity);

		TickerBaseData tDataCopy = new TickerBaseData(tData);

		assertEquals(tDataCopy.getType(), type);
		assertEquals(tDataCopy.getName(), name);
		assertEquals(tDataCopy.getQuantity(), quantity);
	}

	@Test
	public void testGetName() {
		String name = "1234456";

		TickerBaseData tData = new TickerBaseData();
		tData.setName(name);
		assertEquals(tData.getName(), name);
	}

	@Test
	public void testSetName() {
		String name = "AAA";

		TickerBaseData tData = new TickerBaseData();
		tData.setName(name);
		assertEquals(tData.getName(), name);
	}

	@Test
	public void testGetType() {
		TickerType type = TickerType.Equity;

		TickerBaseData tData = new TickerBaseData();
		tData.setType(type);
		assertEquals(tData.getType(), type);
	}

	@Test
	public void testSetType() {
		TickerType type = TickerType.Bond;

		TickerBaseData tData = new TickerBaseData();
		tData.setType(type);
		assertEquals(tData.getType(), type);
	}

	@Test
	public void testGetQuantity() {
		long quantity = 91111222;

		TickerBaseData tData = new TickerBaseData();
		tData.setQuantity(quantity);
		assertEquals(tData.getQuantity(), quantity);
	}

	@Test
	public void testSetQuantity() {
		long quantity = 123;

		TickerBaseData tData = new TickerBaseData();
		tData.setQuantity(quantity);
		assertEquals(tData.getQuantity(), quantity);
	}

}
