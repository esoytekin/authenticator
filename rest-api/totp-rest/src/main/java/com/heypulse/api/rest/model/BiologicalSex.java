package com.heypulse.api.rest.model;

/**
 * Created by emrahsoytekin on 29.10.2017.
 */
public enum BiologicalSex {
    NOTSET("Unknown"),
    FEMALE("Female"),
    MALE("Male"),
    OTHER("Other");

    private String label;

    BiologicalSex(String label) {
        this.label = label;
    }
}
