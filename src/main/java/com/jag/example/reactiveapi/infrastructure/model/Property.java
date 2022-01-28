package com.jag.example.reactiveapi.infrastructure.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @Docuement is useful to identify this POJO as a domain object to be persisted to mongoDB
 */
@Document
public class Property {
    private String code;
    @DateTimeFormat(iso =   DateTimeFormat.ISO.DATE)
    private LocalDate createdAt;
    private Integer rooms;
    private Double rentingPrice;
    private String description;
    private Boolean furnished;
    /**
     * Primary key of the document
     * @Id
     */

    private String id;

    public Property() {
    }

    public Property(String code, LocalDate createdAt, Integer rooms, Double rentingPrice,
                    String description, Boolean furnished) {
        this.code = code;
        this.createdAt = createdAt;
        this.rooms = rooms;
        this.rentingPrice = rentingPrice;
        this.description = description;
        this.furnished = furnished;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Double getRentingPrice() {
        return rentingPrice;
    }

    public void setRentingPrice(Double rentingPrice) {
        this.rentingPrice = rentingPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFurnished() {
        return furnished;
    }

    public void setFurnished(Boolean furnished) {
        this.furnished = furnished;
    }

    public String getId() {
        return id;
    }
}

