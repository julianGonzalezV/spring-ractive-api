package com.jag.example.reactiveapi.domain.service;

import com.jag.example.reactiveapi.infrastructure.model.Property;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Domain Service for business validations
 * FALTA EL MAPPER DE REQUEST A BUSINESS MODEL
 */
@Service
public class PropertyService {
    public Mono<Property> get(String id){
        return null;
    };
    public Mono<Property> create(Mono<Property> property)
    {
        return null;
    };
    public Mono<Property> update(String id, Mono<Property> property){
        return null;
    };
    public Mono<Property> delete(String id){
        return null;
    }
}
