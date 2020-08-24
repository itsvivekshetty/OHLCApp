package com.upstox.ohlcupstox.fsm;

import com.upstox.ohlcupstox.dto.Trade;
import com.upstox.ohlcupstox.dto.TradeOHLCResponse;

/**
 * OHLC Trade Machine to compute ohlc
 * @author vivek
 */
public class TradeOHLCMachine {
	
	private static TradeOHLCResponse oldOhlc;
	
	public TradeOHLCMachine() {
		oldOhlc = new TradeOHLCResponse();
		oldOhlc.setSymbol(null);
		oldOhlc.setVolume(0.0);
	}
	
	public TradeOHLCResponse getOhlc(Trade trade, int barNum) {
		TradeOHLCResponse newOhlc;
		Double tradePrice = trade.getTradePrice();
		if(oldOhlc.getSymbol() == null) {
			newOhlc = new TradeOHLCResponse();
			newOhlc.setBarNum(barNum);
			newOhlc.setOpen(tradePrice);
			newOhlc.setHigh(tradePrice);
			newOhlc.setLow(tradePrice);
			newOhlc.setClose(0.0);
			newOhlc.setEvent("Trade_notify");
			newOhlc.setSymbol(trade.getProductSymbol());
			newOhlc.setVolume(getAggregatedVolume(trade.getTradeQty()));
			newOhlc.setDateInSeconds(trade.getDate());
		}else {
			newOhlc = getNewTrade(trade,barNum);
		}
		deepCopy(newOhlc);
		return newOhlc;
	}
	
	/**
	 * Hold previous trade value
	 * @param newOhlc -new ohlc
	 */
	private void deepCopy(TradeOHLCResponse newOhlc) {
		oldOhlc.setBarNum(newOhlc.getBarNum());
		oldOhlc.setClose(newOhlc.getClose());
		oldOhlc.setEvent(newOhlc.getEvent());
		oldOhlc.setHigh(newOhlc.getHigh());
		oldOhlc.setLow(newOhlc.getLow());
		oldOhlc.setOpen(newOhlc.getOpen());
		oldOhlc.setSymbol(newOhlc.getSymbol());
		oldOhlc.setVolume(newOhlc.getVolume());
	}
	
	/**
	 * compute next trade
	 * @param trade - Trade
	 * @param barNum - bar number
	 * @return TradeOHLCResponse
	 */
	private TradeOHLCResponse getNewTrade(Trade trade, int barNum) {
		Double newTradePrice = trade.getTradePrice();
		TradeOHLCResponse newOhlc = new TradeOHLCResponse();
		newOhlc.setBarNum(barNum);
		newOhlc.setEvent("Trade_notify");
		newOhlc.setOpen(oldOhlc.getOpen());
		newOhlc.setHigh(getHigh(newTradePrice));
		newOhlc.setLow(getLow(newTradePrice));
		newOhlc.setClose(oldOhlc.getClose());
		newOhlc.setSymbol(trade.getProductSymbol());
		newOhlc.setVolume(getAggregatedVolume(trade.getTradeQty()));
		return newOhlc;
	}
	
	/**
	 * Compute low 
	 * @param newTradePrice - Price
	 * @return Double
	 */
	private Double getLow(Double newTradePrice  ) {
		if(newTradePrice < oldOhlc.getLow() ) {
			return newTradePrice;
		}
		return oldOhlc.getLow();
	}
	
	/**
	 * Compute high
	 * @param newTradePrice - price
	 * @return Double
	 */
	private Double getHigh(Double newTradePrice  ) {
		if(newTradePrice > oldOhlc.getHigh() ) {
			return newTradePrice;
		}
		return oldOhlc.getHigh();
	}
	
	/**
	 * Compute volume
	 * @param newVolume - volume
	 * @return Double
	 */
	private Double getAggregatedVolume(Double newVolume) {
		return newVolume + oldOhlc.getVolume();
	}
	
	/**
	 * Reset the old trade when as specific interval i.e, 15 seconds 
	 */
	public void resetOld() {
		oldOhlc.setSymbol(null);
	}
	
}
