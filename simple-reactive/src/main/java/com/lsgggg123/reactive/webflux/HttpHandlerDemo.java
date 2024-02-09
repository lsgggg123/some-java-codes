package com.lsgggg123.reactive.webflux;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

public class HttpHandlerDemo {
    public static void main(String[] args) throws InterruptedException {
        HttpHandler httpHandler = (request, response) -> {
            DataBufferFactory factory = response.bufferFactory();
            DataBuffer dataBuffer = factory.wrap("Hello http handler".getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        };

        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
        HttpServer.create().host("0.0.0.0").port(8080).handle(adapter).bindNow();

        Thread.sleep(60_000L);
    }
}
