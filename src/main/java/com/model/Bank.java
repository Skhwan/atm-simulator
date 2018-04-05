package com.model;

/**
 * Created by khwanchanok on 4/6/2018 AD.
 */
public class Bank {

    private final String type;
    private final int value;

    public Bank(String type, int value){
        this.type = type;
        this.value = value;
    }

    public String getType(){
        return this.type;
    }

    public int getValue(){
        return this.value;
    }

}
