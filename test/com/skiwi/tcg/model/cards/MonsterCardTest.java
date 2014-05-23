
package com.skiwi.tcg.model.cards;

import com.skiwi.tcg.model.objects.FusionStat;
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
        assertEquals(2, monsterCard.decreaseHitpoints(2));
        assertEquals(2, monsterCard.increaseHitpoints(10));  //maxes out
        assertEquals(5, monsterCard.getHitpoints());
        
        MonsterCard monsterCard2 = new MonsterCard("Test", 5, 25, MonsterModus.HEALING);
        assertEquals(15, monsterCard2.decreaseHitpoints(15));
        assertEquals(10, monsterCard2.increaseHitpoints(10));
        assertEquals(20, monsterCard2.getHitpoints());
        
        MonsterCard monsterCard3 = new MonsterCard("Test", 5, 7, MonsterModus.OFFENSIVE);
        assertEquals(2, monsterCard3.decreaseHitpoints(2));
        assertEquals(0, monsterCard3.increaseHitpoints(0));
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
        assertEquals(3, monsterCard.decreaseHitpoints(3));
        assertEquals(2, monsterCard.getHitpoints());
        
        MonsterCard monsterCard2 = new MonsterCard("Test", 5, 20, MonsterModus.HEALING);
        assertEquals(20, monsterCard2.decreaseHitpoints(30)); //bottoms out
        assertEquals(0, monsterCard2.getHitpoints());
        
        MonsterCard monsterCard3 = new MonsterCard("Test", 5, 12, MonsterModus.OFFENSIVE);
        assertEquals(0, monsterCard3.decreaseHitpoints(0));
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
    public void testGetMaximumHitpoints() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        assertEquals(5, monsterCard.getMaximumHitpoints());
    }

    @Test
    public void testGetModus() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        assertEquals(MonsterModus.HEALING, monsterCard.getModus());
    }
    
    @Test
    public void testIsDead() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        assertFalse(monsterCard.isDead());
        
        monsterCard.decreaseHitpoints(5);
        assertTrue(monsterCard.isDead());
    }
    
    @Test
    public void testFuseWith() {
        MonsterCard monsterCard = new MonsterCard("Alpha", 10, 10, MonsterModus.HEALING);
        MonsterCard fusedMonsterCard = new MonsterCard("Beta", 5, 5, MonsterModus.OFFENSIVE);   
        FusionCard fusionCard = new FusionCard("Fuser", 20, FusionStat.ATTACK);
        
        MonsterCard builtCard = monsterCard.fuseWith(fusedMonsterCard, fusionCard);
        assertEquals("Alpha Beta", builtCard.getName());
    }
    
    @Test(expected = NullPointerException.class)
    public void testFuseWithNullFuserMonsterCard() {
        MonsterCard monsterCard = new MonsterCard("Alpha", 10, 10, MonsterModus.HEALING);
        FusionCard fusionCard = new FusionCard("Fuser", 20, FusionStat.ATTACK);
        monsterCard.fuseWith(null, fusionCard);
    }
    
    @Test(expected = NullPointerException.class)
    public void testFuseWithNullFusionCard() {
        MonsterCard monsterCard = new MonsterCard("Alpha", 10, 10, MonsterModus.HEALING);
        MonsterCard fusedMonsterCard = new MonsterCard("Beta", 5, 5, MonsterModus.OFFENSIVE);   
        monsterCard.fuseWith(fusedMonsterCard, null);
    }
    
    @Test
    public void testAttackSurvives() {
        MonsterCard monsterCard = new MonsterCard("Alpha", 10, 10, MonsterModus.HEALING);
        MonsterCard attackerMonsterCard = new MonsterCard("Beta", 5, 5, MonsterModus.OFFENSIVE);
        assertEquals(AttackResult.TARGET_SURVIVES, attackerMonsterCard.attack(monsterCard));
        
        assertEquals("Alpha", monsterCard.getName());
        assertEquals(10, monsterCard.getAttack());
        assertEquals(10, monsterCard.getMaximumHitpoints());
        assertEquals(5, monsterCard.getHitpoints());
        assertEquals(MonsterModus.HEALING, monsterCard.getModus());
        assertFalse(monsterCard.isDead());
        
        assertEquals("Beta", attackerMonsterCard.getName());
        assertEquals(5, attackerMonsterCard.getAttack());
        assertEquals(5, attackerMonsterCard.getMaximumHitpoints());
        assertEquals(5, attackerMonsterCard.getHitpoints());
        assertEquals(MonsterModus.OFFENSIVE, attackerMonsterCard.getModus());
        assertFalse(attackerMonsterCard.isDead());
    }
    
    @Test
    public void testAttackDiesZeroHitpoints() {
        MonsterCard monsterCard = new MonsterCard("Alpha", 10, 10, MonsterModus.HEALING);
        MonsterCard attackerMonsterCard = new MonsterCard("Beta", 10, 10, MonsterModus.OFFENSIVE);
        assertEquals(AttackResult.TARGET_DIES, attackerMonsterCard.attack(monsterCard));
        
        assertEquals("Alpha", monsterCard.getName());
        assertEquals(10, monsterCard.getAttack());
        assertEquals(10, monsterCard.getMaximumHitpoints());
        assertEquals(0, monsterCard.getHitpoints());
        assertEquals(MonsterModus.HEALING, monsterCard.getModus());
        assertTrue(monsterCard.isDead());
        
        assertEquals("Beta", attackerMonsterCard.getName());
        assertEquals(10, attackerMonsterCard.getAttack());
        assertEquals(10, attackerMonsterCard.getMaximumHitpoints());
        assertEquals(10, attackerMonsterCard.getHitpoints());
        assertEquals(MonsterModus.OFFENSIVE, attackerMonsterCard.getModus());
        assertFalse(attackerMonsterCard.isDead());
    }
    
    @Test
    public void testAttackDiesNegativeHitpoints() {
        MonsterCard monsterCard = new MonsterCard("Alpha", 10, 10, MonsterModus.HEALING);
        MonsterCard attackerMonsterCard = new MonsterCard("Beta", 20, 20, MonsterModus.OFFENSIVE);
        assertEquals(AttackResult.TARGET_DIES, attackerMonsterCard.attack(monsterCard));
        
        assertEquals("Alpha", monsterCard.getName());
        assertEquals(10, monsterCard.getAttack());
        assertEquals(10, monsterCard.getMaximumHitpoints());
        assertEquals(0, monsterCard.getHitpoints());
        assertEquals(MonsterModus.HEALING, monsterCard.getModus());
        assertTrue(monsterCard.isDead());
        
        assertEquals("Beta", attackerMonsterCard.getName());
        assertEquals(20, attackerMonsterCard.getAttack());
        assertEquals(20, attackerMonsterCard.getMaximumHitpoints());
        assertEquals(20, attackerMonsterCard.getHitpoints());
        assertEquals(MonsterModus.OFFENSIVE, attackerMonsterCard.getModus());
        assertFalse(attackerMonsterCard.isDead());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testAttackNotInOffensiveModus() {
        MonsterCard monsterCard = new MonsterCard("Alpha", 10, 10, MonsterModus.HEALING);
        MonsterCard attackerMonsterCard = new MonsterCard("Beta", 20, 20, MonsterModus.HEALING);
        attackerMonsterCard.attack(monsterCard);
    }

    @Test
    public void testToString() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        monsterCard.decreaseHitpoints(2);
        assertEquals(MonsterCard.class.getSimpleName() + "(Test, 5, 5, 3, HEALING)", monsterCard.toString());
    }
}