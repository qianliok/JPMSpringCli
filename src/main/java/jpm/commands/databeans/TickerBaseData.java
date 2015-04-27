package jpm.commands.databeans;

import jpm.commands.util.ProfitFormulaCtrl.TickerType;

/**
 * The basic information which is required for all tickers
 * @author Qian
 *
 */
public class TickerBaseData {
	private TickerType type;
	private String name;
	private long quantity;
	
	public TickerBaseData(){
		
	}
	
	public TickerBaseData(TickerType type, String name, long quantity){
		this.type = type;
		this.name = name;
		this.quantity = quantity;
	}
	
	public TickerBaseData(TickerBaseData base){
		this.type = base.type;
		this.name = base.name;
		this.quantity = base.quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TickerType getType() {
		return type;
	}

	public void setType(TickerType type) {
		this.type = type;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

}
