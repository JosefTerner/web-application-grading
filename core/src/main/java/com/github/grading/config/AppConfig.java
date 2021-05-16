package com.github.grading.config;

import com.github.grading.handlers.DefaultHandler;
import com.github.grading.handlers.WebsocketHandler;
import com.github.grading.network.Broker;
import com.github.grading.network.WebsocketConnectionPool;

public class AppConfig {

    private static Broker broker = new Broker();

    private static WebsocketConnectionPool websocketConnectionPool = new WebsocketConnectionPool();

    private static WebsocketHandler websocketHandler = new WebsocketHandler(getWebsocketConnectionPool(), getBroker());

    public static WebsocketHandler getWebsocketHandler() {
        return websocketHandler;
    }

    public static WebsocketConnectionPool getWebsocketConnectionPool() {
        return websocketConnectionPool;
    }

    public static Broker getBroker() {
        return broker;
    }
}
