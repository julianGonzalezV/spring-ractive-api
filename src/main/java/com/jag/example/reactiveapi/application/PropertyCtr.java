package com.jag.example.reactiveapi.application;

import com.jag.example.reactiveapi.domain.repository.PropertyRepository;
import com.jag.example.reactiveapi.infrastructure.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @RestController: This annotation includes the @Controller and @ResponseBody
 * @RequestMapping: Lets create a base string that each endpoint can build upon
 * @CrossOrigin: Sometimes we are testing the front-end in local host but in different PORT that the
 * backend SO TECHNICALLY they are different ORIGINS , BUT TAKE CARE of this annotation in production ENV
 */
@RestController
@RequestMapping(PropertyCtr.MS_PROPERTY_V_1)
@CrossOrigin
public class PropertyCtr {
    public static final String MS_PROPERTY_V_1 = "/v1/ms-property/";
    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyCtr(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    /**
     *  @GetMappin helps to pass a path parameter
     * @return
     */
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Property> getById(@PathVariable  String id){
        return propertyRepository.get(id);
    }

    /**
     * Method to create a record
     * @RequestBody: It deserializes a JSON Request body into an Object
     * @param property
     * @return
     */
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
    public Mono<Property> create(@RequestBody Mono<Property> property){
        return propertyRepository.create(property);
    }

    /**
     * Method to update a record
     * @param property
     * @return
     */
    @PutMapping(path = "{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
    public Mono<Property> save(@PathVariable  String id, @RequestBody Mono<Property> property){
        return propertyRepository.update(id, property);
    }

    /**
     *
     * @param id
     * @return Boolean to indicate if the record was or not deleted
     */
    @DeleteMapping(path = "{id}")
    public Mono<Boolean> deleteId(@PathVariable  String id){
        return propertyRepository.delete(id);
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Property> listAll(){
        return propertyRepository.getAll();
    }
}
