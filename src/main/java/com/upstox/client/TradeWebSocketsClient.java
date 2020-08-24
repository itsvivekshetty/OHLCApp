package com.upstox.client;

import com.upstox.config.OHLCTradeStompSessionHandler;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;

/**
 * Test client to connect and subscribe the live trading
 * @author vivek
 */
public class TradeWebSocketsClient {

	 private static final String URL = "ws://localhost:8080/upstox/websocket";
	 
	 public static void main(String[] args) {
	
		WebSocketClient client = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(client);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		StompSessionHandler sessionHandler = new OHLCTradeStompSessionHandler();
		stompClient.connect(URL, sessionHandler);
		new Scanner(System.in).nextLine();
	}
}
