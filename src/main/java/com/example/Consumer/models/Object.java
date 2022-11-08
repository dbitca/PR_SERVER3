package com.example.Consumer.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Object {
    private int id;
    public Object() {
    }

    @JsonCreator
    public Object(@JsonProperty("id") int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
