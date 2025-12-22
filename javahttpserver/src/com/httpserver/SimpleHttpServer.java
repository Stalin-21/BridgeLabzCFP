package com.httpserver;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServer {

    public static final int DEFAULT_PORT = 9000;
    private HttpServer httpServer;

    public void start(int port) {
        try {
            httpServer = HttpServer.create(new InetSocketAddress(port), 0);
            System.out.println("Server started at port: " + port);

            // Register contexts (routes)
            httpServer.createContext("/", new Handlers.RootHandler());
            httpServer.createContext("/echoHeader", new Handlers.EchoHeaderHandler());
            httpServer.createContext("/echoGet", new Handlers.EchoGetHandler());
            httpServer.createContext("/echoPost", new Handlers.EchoPostHandler());

            httpServer.setExecutor(null); // default executor
            httpServer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SimpleHttpServer server = new SimpleHttpServer();
        server.start(DEFAULT_PORT);

    }
}
