package com.upstox.ohlcupstox.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * Trades data model
 * @author vivek
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Trade {

	@JsonProperty("sym")
	private String productSymbol;

	@JsonProperty("T")
	private String tradeType;

	@JsonProperty("P")
	private Double tradePrice;

	@JsonProperty("Q")
	private Double tradeQty;

	@JsonProperty("TS")
	@JsonIgnore
	private BigInteger ts;

	@JsonProperty("side")
	@JsonIgnore
	private String side;

	@JsonProperty("TS2")
	private String timeStamp;

	public String getProductSymbol() {
		return productSymbol;
	}

	public void setProductSymbol(String productSymbol) {
		this.productSymbol = productSymbol;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public Double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public long getDate()  {
		long seconds=-1;
		if(timeStamp != null){
			long unixNanoSeconds = Long.parseLong(timeStamp);
			seconds = unixNanoSeconds / 1_000_000_000;
			//long nanos = unixNanoSeconds % 1_000_000_000;
			//Instant instant = Instant.ofEpochSecond(seconds, nanos);
			//java.util.Date temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse("2018-10-01T16:02:28.332270624Z");


			//return new Date(instant.toEpochMilli());
		}

		return seconds;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;

	}

	public Double getTradeQty() {
		return tradeQty;
	}

	public void setTradeQty(Double tradeQty) {
		this.tradeQty = tradeQty;
	}

	public BigInteger getTs() {
		return ts;
	}

	public void setTs(BigInteger ts) {
		this.ts = ts;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	@Override
	public String toString() {
		return "Trade [Symbol=" +
				productSymbol +
				", tradeType=" +
				tradeType +
				", tradePrice=" +
				tradePrice +
				", tradeQty=" +
				tradeQty +
				", ts=" +
				ts +
				", side=" +
				side +
				", timeStamp=" +
				timeStamp+
				"]";
	}
}
