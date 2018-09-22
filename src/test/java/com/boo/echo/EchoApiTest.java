package com.boo.echo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@ExtendWith(SpringExtension.class)
@WebFluxTest
class EchoApiTest {


    @Autowired
    private WebTestClient webClient;

    @Test
    void postEcho_hundredtimes_success() {

        Flux.range(1, 100)
                .subscribe(c -> webClient.post().uri("http://localhost/echo?counter=" + c)
                        .exchange()
                        .expectStatus().isOk());

    }
}