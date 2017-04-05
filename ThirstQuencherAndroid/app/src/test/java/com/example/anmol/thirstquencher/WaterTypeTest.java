package com.example.anmol.thirstquencher;

import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.WaterType;

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
 * Created by Wiqas Nassar on 4/5/17.
 *
 */
public class WaterTypeTest {

    private static final int TIMEOUT = 200;

    @Test(timeout = TIMEOUT)
    public void testGetWaterType() {
        assertEquals("getWaterType fails when waterType parameter is Bottled", WaterType.BOTTLED, References.getWaterType("Bottled"));
        assertEquals("getWaterType fails when waterType parameter is Well", WaterType.WELL, References.getWaterType("Well"));
        assertEquals("getWaterType fails when waterType parameter is Stream", WaterType.STREAM, References.getWaterType("Stream"));
        assertEquals("getWaterType fails when waterType parameter is Lake", WaterType.LAKE, References.getWaterType("Lake"));
        assertEquals("getWaterType fails when waterType parameter is Spring",WaterType.SPRING, References.getWaterType("Spring"));
        assertEquals("getWaterType fails when waterType parameter is Other", WaterType.OTHER, References.getWaterType("Other"));
        assertNull("getWaterType fails when waterType parameter is null",References.getWaterType("null"));
    }

}
