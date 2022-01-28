package com.jag.example.reactiveapi.application;
import com.jag.example.reactiveapi.infrastructure.model.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

/**
 * @RunWith:You need this annotation to just
 * enable spring boot features like @Autowire, @MockBean etc.. during junit testing
 *
 * @SpringBootTest: Gives access to the Spring ApplicationContext. This annotation is used to load
 * complete application context for end to end integration testing
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PropertyCtrTest {

    @Autowired
    private ApplicationContext context;
    private WebTestClient webTestClient;
    private Property property;

    /**
     * Method that runs before each test
     */
    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(this.context).build();
        property = new Property(
                "TEST999",
                LocalDate.now(),
                3,
                450000.0,
                "Testing property",
                false
        );
    }


    @Test
    void create() {
        webTestClient
                .post()
                .uri(PropertyCtr.MS_PROPERTY_V_1)
                .body(Mono.just(property), Property.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Property.class);
    }

    @Test
    void listAll() {
        webTestClient
                .get()//GET http Method
                .uri(PropertyCtr.MS_PROPERTY_V_1)
                .exchange()
                .expectBodyList(Property.class); // execute the rest call
    }
}