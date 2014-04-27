
package com.skiwi.tcg.model.cards;

import com.skiwi.tcg.model.objects.MonsterModus;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class MonsterCardTest {
    static {
        assertTrue(true);
    }
    
    @Test
    public void testConstructor() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        assertEquals("Test", monsterCard.getName());
        assertEquals(5, monsterCard.getAttack());
        assertEquals(5, monsterCard.getHitpoints());
        assertEquals(MonsterModus.HEALING, monsterCard.getModus());
        
        MonsterCard monsterCard2 = new MonsterCard("a", 0, 1, MonsterModus.OFFENSIVE);
        assertEquals("a", monsterCard2.getName());
        assertEquals(0, monsterCard2.getAttack());
        assertEquals(1, monsterCard2.getHitpoints());
        assertEquals(MonsterModus.OFFENSIVE, monsterCard2.getModus());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullName() {
        new MonsterCard(null, 5, 5, MonsterModus.HEALING);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEmptyName() {
        new MonsterCard("", 5, 5, MonsterModus.HEALING);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeAttack() {
        new MonsterCard("Test", -1, 5, MonsterModus.HEALING);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorZeroHitpoints() {
        new MonsterCard("Test", 5, 0, MonsterModus.HEALING);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeHitpoints() {
        new MonsterCard("Test", 5, -1, MonsterModus.HEALING);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullMonsterModus() {
        new MonsterCard("Test", 5, -1, null);
    }
    
    @Test
    public void testIncreaseHitpoints() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        monsterCard.decreaseHitpoints(2);
        monsterCard.increaseHitpoints(10);  //caps out
        assertEquals(5, monsterCard.getHitpoints());
        
        MonsterCard monsterCard2 = new MonsterCard("Test", 5, 25, MonsterModus.HEALING);
        monsterCard2.decreaseHitpoints(15);
        monsterCard2.increaseHitpoints(10);
        assertEquals(20, monsterCard2.getHitpoints());
        
        MonsterCard monsterCard3 = new MonsterCard("Test", 5, 7, MonsterModus.OFFENSIVE);
        monsterCard3.decreaseHitpoints(2);
        monsterCard3.increaseHitpoints(0);
        assertEquals(5, monsterCard3.getHitpoints());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIncreaseHitpointsNegativeIncrement() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        monsterCard.increaseHitpoints(-1);
    }

    @Test
    public void testDecreaseHitpoints() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        monsterCard.decreaseHitpoints(3);
        assertEquals(2, monsterCard.getHitpoints());
        
        MonsterCard monsterCard2 = new MonsterCard("Test", 5, 20, MonsterModus.HEALING);
        monsterCard2.decreaseHitpoints(30); //bottoms out
        assertEquals(0, monsterCard2.getHitpoints());
        
        MonsterCard monsterCard3 = new MonsterCard("Test", 5, 12, MonsterModus.OFFENSIVE);
        monsterCard3.decreaseHitpoints(0);
        assertEquals(12, monsterCard3.getHitpoints());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDecreaseHitpointsNegativeDecrement() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        monsterCard.decreaseHitpoints(-1);
    }

    @Test
    public void testSetModus() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        monsterCard.setModus(MonsterModus.OFFENSIVE);
        assertEquals(MonsterModus.OFFENSIVE, monsterCard.getModus());
    }
    
    @Test(expected = NullPointerException.class)
    public void testSetModusNullModus() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        monsterCard.setModus(null);
    }

    @Test
    public void testGetName() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        assertEquals("Test", monsterCard.getName());
    }

    @Test
    public void testGetAttack() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        assertEquals(5, monsterCard.getAttack());
    }

    @Test
    public void testGetHitpoints() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        assertEquals(5, monsterCard.getHitpoints());
    }

    @Test
    public void testGetModus() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        assertEquals(MonsterModus.HEALING, monsterCard.getModus());
    }

    @Test
    public void testHashCode() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        MonsterCard monsterCard2 = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        assertTrue(monsterCard.equals(monsterCard2));
        assertEquals(monsterCard.hashCode(), monsterCard2.hashCode());
        
        MonsterCard monsterCard3 = new MonsterCard("Test", 5, 5, MonsterModus.OFFENSIVE);
        monsterCard3.decreaseHitpoints(1);
        assertTrue(monsterCard.equals(monsterCard3));
        assertEquals(monsterCard.hashCode(), monsterCard3.hashCode());
    }

    @Test
    public void testEquals() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        MonsterCard monsterCard2 = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        assertTrue(monsterCard.equals(monsterCard2));
        
        MonsterCard monsterCard3 = new MonsterCard("Test", 5, 5, MonsterModus.OFFENSIVE);
        monsterCard3.decreaseHitpoints(1);
        assertTrue(monsterCard.equals(monsterCard3));
        
        assertFalse(monsterCard.equals(null));
        
        assertFalse(monsterCard.equals("a"));
        
        MonsterCard monsterCard4 = new MonsterCard("Testt", 5, 5, MonsterModus.HEALING);
        assertFalse(monsterCard.equals(monsterCard4));
        
        MonsterCard monsterCard5 = new MonsterCard("Test", 7, 5, MonsterModus.HEALING);
        assertFalse(monsterCard.equals(monsterCard5));
        
        MonsterCard monsterCard6 = new MonsterCard("Test", 5, 7, MonsterModus.HEALING);
        assertFalse(monsterCard.equals(monsterCard6));
    }

    @Test
    public void testToString() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        monsterCard.decreaseHitpoints(2);
        assertEquals(MonsterCard.class.getSimpleName() + "(Test, 5, 5, 3, HEALING)", monsterCard.toString());
    }
}