package com.upstox.ohlcupstox.dto;

/**
 *  Subscription of trades
 * @author vivek
 */
public class Subscription {


	private String symbol;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "Subscription [symbol=" +
				symbol +
				"]";
	}	
	
}
