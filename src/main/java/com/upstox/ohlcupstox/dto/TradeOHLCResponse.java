package com.upstox.ohlcupstox.dto;

/**
 * Trade OHLC Response
 * @author vivek
 */
public class TradeOHLCResponse {

	private String event;
	private String symbol;
	private int barNum;
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Double volume;
	private long seconds;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getBarNum() {
		return barNum;
	}

	public void setBarNum(int barNum) {
		this.barNum = barNum;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public void setDateInSeconds(long date) {
		this.seconds = date;
	}
	public long getDateInSeconds(){
		return seconds;
	}

	@Override
	public String toString() {
		return "TradeOHLCResponse [event=" +
				event +
				", symbol=" +
				symbol +
				", barNum=" +
				barNum +
				", open=" +
				open +
				", high=" +
				high +
				", low=" +
				low +
				", close=" +
				close +
				", volume=" +
				volume +
				"]";
	}


}
