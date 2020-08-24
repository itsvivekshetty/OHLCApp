package com.upstox.ohlcupstox.trades.consumer;

import com.upstox.constant.AppConstants;
import com.upstox.ohlcupstox.dto.Trade;
import com.upstox.ohlcupstox.dto.TradeOHLCResponse;
import com.upstox.ohlcupstox.fsm.TradeOHLCMachine;
import com.upstox.ohlcupstox.trades.broker.TradeBroker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * Trade Consumer - Takes Trade from TradeBroker
 * @author vivek
 */
public class TradeBarChartConsumer implements Runnable{


	private final Logger log = LogManager.getLogger(TradeBarChartConsumer.class);
	
	private final TradeBroker tradeBroker;
	
	private final TradeOHLCMachine tradeOHLCMachine;
	
	private int barNum = 1;
	
	private final SimpMessagingTemplate simpMessagingTemplate;
	
	public TradeBarChartConsumer(TradeBroker tradeBroker, SimpMessagingTemplate simpMessagingTemplate){
		this.tradeBroker = tradeBroker;
		this.tradeOHLCMachine = new TradeOHLCMachine();
		this.simpMessagingTemplate = simpMessagingTemplate;
	}
	@Override
	public void run() {
		int count = 1;
		try {


			long startTradeTimeInSeconds = -1;
			long endTradeTimeInSeconds = -1;
			log.info("startTradeTimeInSeconds->",startTradeTimeInSeconds);
			while(true) {
                Trade  trade = tradeBroker.get();
				if(startTradeTimeInSeconds == -1) {
					startTradeTimeInSeconds = trade.getDate();
				}
				if(endTradeTimeInSeconds == -1){
					endTradeTimeInSeconds = startTradeTimeInSeconds + 15;
				}

                if(trade.getProductSymbol().equals(AppConstants.LAST_TRADE)) {
                	break;
                }
                TradeOHLCResponse ohldRes  = tradeOHLCMachine.getOhlc(trade, barNum);
                long currentTradeTimeInSeconds = trade.getDate();

                if(currentTradeTimeInSeconds >= endTradeTimeInSeconds) {
					startTradeTimeInSeconds = currentTradeTimeInSeconds;
                	endTradeTimeInSeconds = -1;
                	ohldRes.setClose(trade.getTradePrice());
					//log.info("OHLC response :{}",ohldRes);
					simpMessagingTemplate.convertAndSend("/topic/ohlc", ohldRes);
                	barNum++;
                	tradeOHLCMachine.resetOld();
                }
                count++;
                log.info("All Trade OHLC response :{}",ohldRes);
                
               // simpMessagingTemplate.convertAndSend("/topic/ohlc", ohldRes);
			}
			log.info("count: {}",count );
			log.info("Done, Terminating consumer");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
