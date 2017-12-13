package com.heypulse.api.rest.model;

/**
 * Created by emrahsoytekin on 29.10.2017.
 */
public enum BloodType {
    NOTSET("Unknown"),
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("0+"),
    O_NEGATIVE("0-");

    private String label;
    BloodType(String label){
        this.label = label;
    }
}
