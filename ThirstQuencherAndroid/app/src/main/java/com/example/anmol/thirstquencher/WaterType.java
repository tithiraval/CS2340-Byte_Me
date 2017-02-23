package com.example.anmol.thirstquencher;

/**
 * Created by Shaurye on 2/23/17.
 */

public enum WaterType {
    BOTTLED("Bottled"), WELL("Well"), STREAM("Stream"), LAKE("Lake"), SPRING("Spring")
        , OTHER("Other");

    private String type;

    WaterType (String type) {this.type = type;}

    public String toString() {
        return type;
    }
}
