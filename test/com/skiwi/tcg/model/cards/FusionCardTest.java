
package com.skiwi.tcg.model.cards;

import com.skiwi.tcg.model.objects.FusionStat;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class FusionCardTest {
    static {
        assertTrue(true);
    }

    @Test
    public void testConstructor() {
        FusionCard fusionCard = new FusionCard("Test", 5, FusionStat.ATTACK);
        assertEquals("Test", fusionCard.getName());
        assertEquals(5, fusionCard.getFusionPower());
        assertEquals(FusionStat.ATTACK, fusionCard.getFusionStat());
        
        FusionCard fusionCard2 = new FusionCard("r", 1, FusionStat.HITPOINTS);
        assertEquals("r", fusionCard2.getName());
        assertEquals(1, fusionCard2.getFusionPower());
        assertEquals(FusionStat.HITPOINTS, fusionCard2.getFusionStat());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullName() {
        new FusionCard(null, 5, FusionStat.ATTACK);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEmptyname() {
        new FusionCard("", 5, FusionStat.ATTACK);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorZeroFusionPower() {
        new FusionCard("Test", 0, FusionStat.ATTACK);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeFusionPower() {
        new FusionCard("Test", -1, FusionStat.ATTACK);
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullFusionStat() {
        new FusionCard("Test", 5, null);
    }

    @Test
    public void testGetName() {
        FusionCard fusionCard = new FusionCard("Test", 5, FusionStat.ATTACK);
        assertEquals("Test", fusionCard.getName());
    }

    @Test
    public void testGetFusionPower() {
        FusionCard fusionCard = new FusionCard("Test", 5, FusionStat.ATTACK);
        assertEquals(5, fusionCard.getFusionPower());
    }

    @Test
    public void testGetFusionStat() {
        FusionCard fusionCard = new FusionCard("Test", 5, FusionStat.ATTACK);
        assertEquals(FusionStat.ATTACK, fusionCard.getFusionStat());
    }

    @Test
    public void testToString() {
        FusionCard fusionCard = new FusionCard("Test", 5, FusionStat.ATTACK);
        assertEquals(FusionCard.class.getSimpleName() + "(Test, 5, ATTACK)", fusionCard.toString());
    }
}