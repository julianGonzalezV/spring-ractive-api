package com.jag.example.reactiveapi.infrastructure.repository;

import com.jag.example.reactiveapi.domain.repository.PropertyRepository;
import com.jag.example.reactiveapi.infrastructure.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Repository
public class MongoImpl  implements PropertyRepository {
    private final ReactiveMongoTemplate template;

    @Autowired
    public MongoImpl(ReactiveMongoTemplate template) {
        this.template = template;
    }

    @Override
    public Mono<Property> get(String id) {
        return template.findById(id,Property.class);
    }


    @Override
    public Mono<Property> create(Mono<Property> property) {
        System.out.println("llega al repo Mongo");
        // return  Mono.just(new Property());
        return template.save(property)
                .onErrorResume(e -> {
                    System.out.println("Error...");
                    System.out.println(e.getMessage());
                    return property;
                } );
    }

    @Override
    public Mono<Property> update(String id, Mono<Property> property) {
        /**
         * Upsert Version
         */
        // return reactiveMongoOperations.save(property);

        /**
         * For updating only one attribute
         */
        return property.flatMap( p -> template.findAndModify(
                        Query.query(Criteria.where("id").is(id)),
                        Update.update("furnished", p.getFurnished()), Property.class
                ).flatMap( result -> {
                    result.setFurnished(p.getFurnished());
                    return  Mono.just(result);
                })
        );
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Mono<Boolean> delete(String id) {
        System.out.println("delete.....");
        return template
                .remove(Query.query(Criteria.where("id").is(id)), Property.class)
                .flatMap(result -> Mono.just(result.wasAcknowledged()));
    }

    @Override
    public Flux<Property> getAll() {
        return template
                .findAll(Property.class)
                .delayElements(Duration.ofSeconds(2));
    }
}
