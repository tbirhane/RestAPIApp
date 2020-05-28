package com.javainterview.java_apple.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Value {

    @Id
    private int id;
    private String quote;

    public Value(){}
    public Value(int id, String quote){
        this.id = id;
        this.quote = quote;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
