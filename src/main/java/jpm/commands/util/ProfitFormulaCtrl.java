package jpm.commands.util;

import java.text.DecimalFormat;

import jpm.commands.databeans.TickerPriceData;

/**
 * Profit calculation formula class
 * @author Qian
 *
 */
public class ProfitFormulaCtrl {
	public static enum TickerType {
		Equity, Bond
	};

	public static final DecimalFormat decFormatter = new DecimalFormat("###.##");

	public static double calcTickerData(TickerPriceData tData) {
		double profit;
		switch (tData.getType()) {
		case Equity:
			profit = simpleEquity(tData.getQuantity(), tData.getBuyPrice(), tData.getSellPrice());
			break;
		case Bond:
			profit = simpleBond(tData.getQuantity(), tData.getCoupon());
			break;
		default:
			profit = 0;
		}
			
		return profit;
	}

	public static double simpleEquity(long quantity, double buyPrice, double sellPrice) {
		double profit = (sellPrice - buyPrice) * quantity;
		return profit;
	}

	public static double simpleBond(long quantity, double coupon) {
		double profit = quantity * coupon;
		return profit;
	}
}
