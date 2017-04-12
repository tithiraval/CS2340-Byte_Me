package com.example.anmol.thirstquencher.Model;

/**
 * Enum to hold the different conditions water can be in
 * @author Shaurye
 * @version 2/23/17
 */


public enum WaterCondition {
    WASTE("Waste"), TREATABLE_CLEAR("Treatable-Clear"), TREATABLE_MUDDY("Treatable-Muddy"), PORTABLE("Potable");
    private String cond;

    /**
     * sets the string representation of the enum value
     * @param cond condition of water
     */
    WaterCondition(String cond) {this.cond = cond;}

    @Override
    public String toString() {
        return cond;
    }

}
