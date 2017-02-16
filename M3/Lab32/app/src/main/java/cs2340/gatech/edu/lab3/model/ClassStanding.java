package cs2340.gatech.edu.lab3.model;

/**
 * Created by Shaurye on 2/7/17.
 */

public enum ClassStanding {
    FRESHMAN("FR"), SOPHOMORE("SO"), JUNIOR("JR"), SENIOR("SR");
    private String a;
    ClassStanding (String b) {
        a = b;
    }

    public String toString() {
        return a;
    }
}

