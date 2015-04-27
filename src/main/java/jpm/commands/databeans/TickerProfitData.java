package jpm.commands.databeans;

import jpm.commands.util.ProfitFormulaCtrl;

/**
 * TickerProfitData which only contains the essential summary data
 * @author Qian 
 *
 */
public class TickerProfitData extends TickerBaseData {
	private double profit;
	
	public TickerProfitData(TickerBaseData base){
		super(base);
	}
	
	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}
	
	public String toString(){
		 return getType()+","+getName()+","+getQuantity()+","+ProfitFormulaCtrl.decFormatter.format(profit);
	}
	
}
