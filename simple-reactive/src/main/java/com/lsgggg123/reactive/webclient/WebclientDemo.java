package com.lsgggg123.reactive.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Slf4j
public class WebclientDemo {

    static final WebClient WEB_CLIENT = WebClient.builder().build();
    static final String URI = "http://127.0.0.1:8000/index";

    public static void main(String[] args) throws InterruptedException {
        Mono<String> mono = WEB_CLIENT
                .get()
                .uri(URI)
                .retrieve()
                .bodyToMono(String.class)
                .map(s -> {
                    log.info("map: {}");
                    return s;
                }).onErrorResume(t -> Mono.just("error")).doOnError(Throwable::printStackTrace);

        mono.subscribe(s -> log.info("http response: {}", s));
        TimeUnit.SECONDS.sleep(60);
    }
}