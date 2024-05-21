package com.santiagoprogrammer.Tutorial.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class UserData {

    public String name;

    @JsonIgnore
    public int age;

    @JsonProperty("real_address")
    public String address;

    //@JsonValue
    @JsonGetter("information")
    public String info() {
        return "Username is " + name + " and age is " + age + " and address is " + address;
    }

    public UserData(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
