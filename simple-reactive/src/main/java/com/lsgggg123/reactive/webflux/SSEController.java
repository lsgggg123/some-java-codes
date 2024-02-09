package com.lsgggg123.reactive.webflux;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class SSEController {

    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> sse() {
        return Flux.range(1, 10)
                .map(i -> ServerSentEvent
                        .<String>builder()
                        .id(String.valueOf(i))
                        .data("data-" + i)
                        .build())
                .delayElements(Duration.ofMillis(200L));
    }
}
