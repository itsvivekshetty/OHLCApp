package com.upstox.ohlcupstox.trades.broker;

import com.upstox.ohlcupstox.dto.Subscription;
import com.upstox.ohlcupstox.dto.Trade;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Trade Broker acts as common shared resource between producer consumer,
 * @author vivek
 */
public class TradeBroker {
	
	public ArrayBlockingQueue<Trade> queue = new ArrayBlockingQueue<>(100);
	private Subscription subscription;
	
	public TradeBroker(Subscription subscription){
		this.subscription = subscription;
	}
 
    public void put(Trade data) throws InterruptedException
    {
        this.queue.put(data);
    }
 
    public Trade get() throws InterruptedException
    {
        return this.queue.take();
    }
    
    public Subscription getSubscription()
    {
        return this.subscription;
    }
}
