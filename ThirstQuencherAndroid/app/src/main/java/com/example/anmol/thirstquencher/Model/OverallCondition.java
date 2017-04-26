package com.example.anmol.thirstquencher.Model;

/**
 * Enum of the different conditions of water
 * @author Tithi
 * @version 3/16/17
 */

public enum OverallCondition {
    SAFE("Safe"), Treatable("Treatable"), UNSAFE("Unsafe");
    private String cond;

    /**
     * sets the string representation of the enum value
     * @param cond condition of water
     */
    OverallCondition(String cond) {this.cond = cond;}

    /**
     * returns a string of the water condition
     * @return water condition
     */
    @Override
    public String toString() {
        return cond;
    }
}
