package com.httpserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Handlers {

    // ================= Root Handler =================
    public static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "<h1>Server started successfully</h1>"
                    + "<h2>Port: 9000</h2>";

            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    // ================= Header Handler =================
    public static class EchoHeaderHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Headers headers = exchange.getRequestHeaders();
            Set<Map.Entry<String, List<String>>> entries = headers.entrySet();

            StringBuilder response = new StringBuilder();

            for (Map.Entry<String, List<String>> entry : entries) {
                response.append(entry.getKey())
                        .append(" : ")
                        .append(entry.getValue())
                        .append("\n");
            }

            exchange.sendResponseHeaders(200, response.toString().getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }

    // ================= GET Handler =================
    public static class EchoGetHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Map<String, Object> parameters = new HashMap<>();

            URI requestURI = exchange.getRequestURI();
            String query = requestURI.getRawQuery();
            parseQuery(query, parameters);

            StringBuilder response = new StringBuilder();
            for (String key : parameters.keySet()) {
                response.append(key)
                        .append(" = ")
                        .append(parameters.get(key))
                        .append("\n");
            }

            exchange.sendResponseHeaders(200, response.toString().getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }

    // ================= POST Handler =================
    public static class EchoPostHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Map<String, Object> parameters = new HashMap<>();

            InputStreamReader isr =
                    new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String query = br.readLine();

            parseQuery(query, parameters);

            StringBuilder response = new StringBuilder();
            for (String key : parameters.keySet()) {
                response.append(key)
                        .append(" = ")
                        .append(parameters.get(key))
                        .append("\n");
            }

            exchange.sendResponseHeaders(200, response.toString().getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }

    // ================= Utility Method =================
    private static void parseQuery(String query, Map<String, Object> parameters)
            throws UnsupportedEncodingException {

        if (query == null) return;

        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] param = pair.split("=");

            String key = URLDecoder.decode(param[0], "UTF-8");
            String value = param.length > 1
                    ? URLDecoder.decode(param[1], "UTF-8")
                    : "";

            parameters.put(key, value);
        }
    }
}