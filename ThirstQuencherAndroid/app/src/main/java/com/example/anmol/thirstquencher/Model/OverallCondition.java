package com.example.anmol.thirstquencher.Model;

/**
 * Created by Tithi on 3/16/17.
 */

public enum OverallCondition {
    SAFE("Safe"), Treatable("Treatable"), UNSAFE("Unsafe");
    private String cond;

    /**
     * sets the string representation of the enum value
     * @param cond condition of water
     */
    OverallCondition(String cond) {this.cond = cond;}

    @Override
    public String toString() {
        return cond;
    }
}
