package com.jag.example.reactiveapi.domain.repository;

import com.jag.example.reactiveapi.infrastructure.model.Property;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface that abstract the interactions with data storage
 */
public interface PropertyRepository {
    Mono<Property> get(String id);
    Mono<Property> create(Mono<Property> property);
    Mono<Property> update(String id, Mono<Property> property);
    Mono<Boolean> delete(String id);
    Flux<Property> getAll();
}
