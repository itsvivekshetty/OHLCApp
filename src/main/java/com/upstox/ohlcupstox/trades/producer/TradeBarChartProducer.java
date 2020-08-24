package com.upstox.ohlcupstox.trades.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upstox.constant.AppConstants;
import com.upstox.ohlcupstox.dto.Trade;
import com.upstox.ohlcupstox.trades.broker.TradeBroker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

/**
 *  Producer Thread--> Puts trade  onto Trade broker
 * @author vivek
 */
public class TradeBarChartProducer implements Runnable{

	private final Logger log = LogManager.getLogger(TradeBarChartProducer.class);
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	private final TradeBroker tradeBroker;
	
	public TradeBarChartProducer(TradeBroker tradeBroker) {
		this.tradeBroker = tradeBroker;	
	}

	private static final String TRADE_FILE_PATH = "trades.json";
	
	@Override
	public void run() {
		try {
			try (BufferedReader reader = new BufferedReader(new FileReader(TRADE_FILE_PATH))) {
				String line = reader.readLine();
				while (line != null) {
					line = reader.readLine();
					if (line != null && !line.equals("")) {
						Trade trade = getTrade(line);
						if (tradeBroker.getSubscription().getSymbol().equals(trade.getProductSymbol())) {
							tradeBroker.put(trade);
						}
					}
				}
				Trade lastTrade = new Trade();
				lastTrade.setProductSymbol(AppConstants.LAST_TRADE);
				tradeBroker.put(lastTrade);
			}
			log.info("Done, Terminating Producer");
		} catch (IOException e) {
			log.error("Error Occur getting the trade data: {}",e.getMessage());
		} catch (InterruptedException e) {
			log.error("Error Occur while producesing the trade: {}",e.getMessage());
		}
	}
	
	
	/**
	 * Map tarde json to Trade POJO
	 * @param json  -Json string
	 * @return Trade
	 * @throws JsonProcessingException Exception
	 */
	private static Trade getTrade(String json) throws JsonProcessingException{
		TypeReference<Trade> tradeListRef = new TypeReference<Trade>() { };		
		return objectMapper.readValue(json, tradeListRef);	
	}
}
