package com.example.anmol.thirstquencher.model;

/**
 * Enum to hold the different sources of water
 * @author Shaurye
 * @version 2/23/17
 */


public enum WaterType {
    BOTTLED("Bottled"), WELL("Well"), STREAM("Stream"), LAKE("Lake"), SPRING("Spring")
        , OTHER("Other");

    private final String type;

    /**
     * sets the string representation of the enum value
     * @param type source of water
     */
    WaterType (String type) {this.type = type;}

    @Override
    public String toString() {
        return type;
    }
}
