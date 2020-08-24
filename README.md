# Read Me 

Spring boot Websocket project with Maven

# Build 

`mvn clean install`

# How to start the application

* Go to ``UpstoxOHApp.java`` and run using java main method

# For handshaking 

* ``ws://localhost:8080/upstox/websocket``

# Websocker Configuration to check the end-points configuration 

* ``WebSocketConfig.java``

# Websocket Controller 

* ``TradeController.java`` have websocket endpoint /app/trades

# Subscription Endpoint

* /topic/ohlc

# How to test

**Step 1:** Start the ``UpstoxOhlcApp.java`` application using java main method.

**Step 1:** Start the test ``WebsocketClient.java`` Client using java main method.

**How its works**

* ``OHLCTradeStompSessionHandler.java`` is the test handler which contains  ``Subscription.java`` and handleFrame method show the subscribed data (``TradeOHLCResponse.java``). 

