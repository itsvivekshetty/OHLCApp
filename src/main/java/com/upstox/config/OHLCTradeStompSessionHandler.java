package com.upstox.config;

import com.upstox.ohlcupstox.dto.Subscription;
import com.upstox.ohlcupstox.dto.TradeOHLCResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

/**
 * This class is an implementation for StompSessionHandlerAdapter.
 * Once a connection is established, it subscribes to /topic/ohlc and
 * send a  message to server.
 * @author vivek
 */
public class OHLCTradeStompSessionHandler extends StompSessionHandlerAdapter {

    private final Logger log = LogManager.getLogger(OHLCTradeStompSessionHandler.class);

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("New session established : " + session.getSessionId());
        session.subscribe("/topic/ohlc", this);
        log.info("Subscribed to /topic/ohlc");
        session.send("/app/trades", getTradeSubscription());
        log.info("Message sent to web socket server");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Got an exception", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return TradeOHLCResponse.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
    	TradeOHLCResponse tradeOHLCResponse = (TradeOHLCResponse) payload;
        log.info("TradeOHLCResponse Received : -> {} ",tradeOHLCResponse);
    }

    /**
     * A sample subscription
     */
    private Subscription getTradeSubscription() {
    	Subscription subscription= new Subscription();
	 	subscription.setSymbol("XXBTZUSD");
        return subscription;
    }
}