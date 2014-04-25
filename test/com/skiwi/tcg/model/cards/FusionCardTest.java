
package com.skiwi.tcg.model.cards;

import com.skiwi.tcg.model.objects.MonsterStat;
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
        FusionCard fusionCard = new FusionCard("Test", 5, MonsterStat.ATTACK);
        assertEquals("Test", fusionCard.getName());
        assertEquals(5, fusionCard.getFusionPower());
        assertEquals(MonsterStat.ATTACK, fusionCard.getFusionStat());
        
        FusionCard fusionCard2 = new FusionCard("r", 1, MonsterStat.HITPOINTS);
        assertEquals("r", fusionCard2.getName());
        assertEquals(1, fusionCard2.getFusionPower());
        assertEquals(MonsterStat.HITPOINTS, fusionCard2.getFusionStat());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullName() {
        new FusionCard(null, 5, MonsterStat.ATTACK);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEmptyname() {
        new FusionCard("", 5, MonsterStat.ATTACK);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorZeroFusionPower() {
        new FusionCard("Test", 0, MonsterStat.ATTACK);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeFusionPower() {
        new FusionCard("Test", -1, MonsterStat.ATTACK);
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullFusionStat() {
        new FusionCard("Test", 5, null);
    }

    @Test
    public void testGetName() {
        FusionCard fusionCard = new FusionCard("Test", 5, MonsterStat.ATTACK);
        assertEquals("Test", fusionCard.getName());
    }

    @Test
    public void testGetFusionPower() {
        FusionCard fusionCard = new FusionCard("Test", 5, MonsterStat.ATTACK);
        assertEquals(5, fusionCard.getFusionPower());
    }

    @Test
    public void testGetFusionStat() {
        FusionCard fusionCard = new FusionCard("Test", 5, MonsterStat.ATTACK);
        assertEquals(MonsterStat.ATTACK, fusionCard.getFusionStat());
    }

    @Test
    public void testHashCode() {
        FusionCard fusionCard = new FusionCard("Test", 5, MonsterStat.ATTACK);
        FusionCard fusionCard2 = new FusionCard("Test", 5, MonsterStat.ATTACK);
        assertTrue(fusionCard.equals(fusionCard2));
        assertEquals(fusionCard.hashCode(), fusionCard2.hashCode());
    }

    @Test
    public void testEquals() {
        FusionCard fusionCard = new FusionCard("Test", 5, MonsterStat.ATTACK);
        FusionCard fusionCard2 = new FusionCard("Test", 5, MonsterStat.ATTACK);
        assertTrue(fusionCard.equals(fusionCard2));
        
        assertFalse(fusionCard.equals(null));
        
        assertFalse(fusionCard.equals("a"));
        
        FusionCard fusionCard3 = new FusionCard("Testt", 5, MonsterStat.ATTACK);
        assertFalse(fusionCard.equals(fusionCard3));
        
        FusionCard fusionCard4 = new FusionCard("Test", 7, MonsterStat.ATTACK);
        assertFalse(fusionCard.equals(fusionCard4));
        
        FusionCard fusionCard5 = new FusionCard("Test", 5, MonsterStat.HITPOINTS);
        assertFalse(fusionCard.equals(fusionCard5));
    }

    @Test
    public void testToString() {
        FusionCard fusionCard = new FusionCard("Test", 5, MonsterStat.ATTACK);
        assertEquals("FusionCard(Test, 5, ATTACK)", fusionCard.toString());
    }
}