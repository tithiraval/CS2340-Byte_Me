package com.example.anmol.thirstquencher;

import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.WaterCondition;

import org.junit.Before;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


import java.lang.ref.Reference;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/**
 * Created by Shaurye Aggarwal on 4/5/17.
 * Tests the getWaterCondition method in the References class.
 */
public class WaterConditionTests {

    private static final int TIMEOUT = 200;

    @Test(timeout = TIMEOUT)
    public void testGetWaterCondition() {
        assertEquals("Fails when waterCondition parameter is Waste", WaterCondition.WASTE, References.getWaterCondition("Waste"));
        assertEquals("Fails when waterCondition parameter is Treatable-Clear", WaterCondition.TREATABLE_CLEAR, References.getWaterCondition("Treatable-Clear"));
        assertEquals("Fails when waterCondition parameter is Treatable-Muddy", WaterCondition.TREATABLE_MUDDY, References.getWaterCondition("Treatable-Muddy"));
        assertEquals("Fails when waterCondition parameter is Portable", WaterCondition.PORTABLE, References.getWaterCondition("Portable"));
        assertNull("Fails when waterCondition parameter is null",References.getWaterCondition("null"));
    }

}
