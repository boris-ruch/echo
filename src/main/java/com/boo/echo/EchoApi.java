package com.boo.echo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class EchoApi {

    private AtomicInteger getEchoCounter = new AtomicInteger();

    private AtomicInteger postEchoCounter = new AtomicInteger();

    private static final Logger LOGGER = LogManager.getLogger();

    @GetMapping("/echo")
    public Mono<Long> getEcho() {
        long counter = getEchoCounter.incrementAndGet();
        LOGGER.info("call /echo: '{}'", counter);
        return Mono.just(counter);
    }

    @PostMapping("/echo")
    public Mono<Echo> postEcho(@RequestParam("counter") long counter) {
        Echo echo = Echo
                .builder()
                .passedValue(counter)
                .requestCounter(postEchoCounter.incrementAndGet())
                .localDateTime(LocalDateTime.now())
                .build();
        LOGGER.info("call POST /echo:'{}'", echo);
        return Mono.just(echo);
    }

}
