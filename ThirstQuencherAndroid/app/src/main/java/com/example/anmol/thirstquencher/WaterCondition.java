package com.example.anmol.thirstquencher;

/**
 * Created by Shaurye on 2/23/17.
 */

public enum WaterCondition {
    WASTE("Waste"), TREATABLE_CLEAR("Treatable-Clear"), TREATABLE_MUDDY("Treatable-Muddy"), PORTABLE("Potable");
    private String cond;

    WaterCondition(String cond) {this.cond = cond;}

    public String toString() {
        return cond;
    }

}
