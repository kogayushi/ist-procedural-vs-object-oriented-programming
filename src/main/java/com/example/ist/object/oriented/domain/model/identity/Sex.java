package com.example.ist.object.oriented.domain.model.identity;

public enum Sex {
    MALE,
    FEMALE,
    UNKNOWN;
    
    public static Sex of(String name) {
        if(name == null) {
            return UNKNOWN;
        }
        for(Sex value : values()) {
            if(value.name().equalsIgnoreCase(name.toUpperCase())) {
                return value;
            }
        }
        throw new IllegalArgumentException("no such a sex : " + name);
    }
}
