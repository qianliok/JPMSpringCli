package jpm.commands.databeans;

import jpm.commands.util.ProfitFormulaCtrl.TickerType;

/**
 * TickerPriceData contain the base ticker information, as well as the price information
 * @author Qian 
 *
 */
public class TickerPriceData extends TickerBaseData {

	private double buyPrice;
	private double sellPrice;
	private double coupon;

	/**
	 * Builder pattern for easier manipulation 
	 * @author Qian Li
	 *
	 */
	public static class TickerPriceBuilder{
		//Required parameters
		private TickerType type;
		private String name;
		private long quantity;
		
		//Optional Parameters
		private double buyPrice;
		private double sellPrice;
		private double coupon;
		
		public TickerPriceBuilder(TickerType type, String name, long quantity){
			this.type = type;
			this.name = name;
			this.quantity = quantity;
		}
		
		public TickerPriceBuilder buyPrice(double val){
			buyPrice=val; return this;
		}
		public TickerPriceBuilder sellPrice(double val){
			sellPrice=val; return this;
		}
		public TickerPriceBuilder coupon(double val){
			coupon=val; return this;
		}
		
		public TickerPriceData build(){
			return new TickerPriceData(this);
		}
	}
	
	public TickerPriceData(TickerPriceBuilder builder) {
			super(builder.type, builder.name, builder.quantity);
			buyPrice=builder.buyPrice;
			sellPrice=builder.sellPrice;
			coupon=builder.coupon;					
	}

	public TickerBaseData getBaseData() {
		return (TickerBaseData)this;
	}
	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public double getCoupon() {
		return coupon;
	}

	public void setCoupon(double coupon) {
		this.coupon = coupon;
	}
}