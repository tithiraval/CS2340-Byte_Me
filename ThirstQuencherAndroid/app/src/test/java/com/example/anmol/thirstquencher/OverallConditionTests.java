package com.example.anmol.thirstquencher;

import com.example.anmol.thirstquencher.Model.OverallCondition;
import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.WaterCondition;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/**
 * Created by Dor Hananel
 * Tests the getCondition method in the References class.
 */
public class OverallConditionTests {

    private static final int TIMEOUT = 200;

    @Test(timeout = TIMEOUT)
    public void testGetWaterCondition() {
        assertEquals("Fails when OverallCondition parameter is Safe", OverallCondition.SAFE, References.getCondition("Safe"));
        assertEquals("Fails when OverallCondition parameter is Treatable", OverallCondition.Treatable, References.getCondition("Treatable"));
        assertEquals("Fails when OverallCondition parameter is Unsafe", OverallCondition.UNSAFE, References.getCondition("Unsafe"));
        assertNull("Fails when OverallCondition parameter is null",References.getCondition("null"));
    }

}
