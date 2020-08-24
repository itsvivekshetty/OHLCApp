package com.upstox.ohlcupstox.controller;

import com.upstox.ohlcupstox.dto.Subscription;
import com.upstox.ohlcupstox.trades.broker.TradeBroker;
import com.upstox.ohlcupstox.trades.consumer.TradeBarChartConsumer;
import com.upstox.ohlcupstox.trades.producer.TradeBarChartProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Web Socket controller for live trading data
 * @author vivek
 */
@Controller
public class TradeController {	 
	 
	@Autowired
	private SimpMessagingTemplate  simpMessagingTemplate;
	 
	@MessageMapping("/trades")
	 public void tradesOHLC(final Subscription subscription)  {
			TradeBroker tradeBroker = new TradeBroker(subscription);
			TradeBarChartProducer producer = new TradeBarChartProducer(tradeBroker);
			TradeBarChartConsumer consumer = new TradeBarChartConsumer(tradeBroker,simpMessagingTemplate);
			ExecutorService service = Executors.newFixedThreadPool(2);
			service.execute(producer);
			service.execute(consumer);
	 }


}