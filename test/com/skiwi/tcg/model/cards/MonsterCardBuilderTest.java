
package com.skiwi.tcg.model.cards;

import com.skiwi.tcg.model.objects.MonsterModus;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class MonsterCardBuilderTest {
    private MonsterCardBuilder builder;
    
    static {
        assertTrue(true);
    }
    
    @Before
    public void before() {
        builder = new MonsterCardBuilder();
    }
    
    @Test
    public void testConstructorNoArgument() {
        new MonsterCardBuilder();
    }
    
    @Test
    public void testConstructorSource() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.OFFENSIVE);
        monsterCard.decreaseHitpoints(2);
        MonsterCard builtCard = new MonsterCardBuilder(monsterCard).build();
        assertEquals(monsterCard.getName(), builtCard.getName());
        assertEquals(monsterCard.getAttack(), builtCard.getAttack());
        assertEquals(monsterCard.getMaximumHitpoints(), builtCard.getMaximumHitpoints());
        assertEquals(monsterCard.getModus(), builtCard.getModus());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullSource() {
        new MonsterCardBuilder(null);
    }
       public void testName() {
        assertNotNull(builder.name("Test"));
    }
    
    @Test(expected = NullPointerException.class)
    public void testNameNullName() {
        builder.name(null);
    }
    
    @Test
    public void testAttack() {
        assertNotNull(builder.attack(5));
    }
    
    @Test
    public void testMaximumHitpoints() {
        assertNotNull(builder.maximumHitpoints(5));
    }
    
    @Test
    public void testModus() {
        assertNotNull(builder.modus(MonsterModus.HEALING));
    }
    
    @Test(expected = NullPointerException.class)
    public void testModusNullModus() {
        builder.modus(null);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testBuildNotAllRequiredFields() {
        builder.build();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testBuildNameMissingFromRequiredFields() {
        builder
                .attack(5)
                .maximumHitpoints(5)
                .modus(MonsterModus.HEALING)
                .build();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testBuildAttackMissingFromRequiredFields() {
        builder
                .name("Test")
                .maximumHitpoints(5)
                .modus(MonsterModus.HEALING)
                .build();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testBuildMaximumHitpointsMissingFromRequiredFields() {
        builder
                .name("Test")
                .attack(5)
                .modus(MonsterModus.HEALING)
                .build();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testBuildModusMissingFromRequiredFields() {
        builder
                .name("Test")
                .attack(5)
                .maximumHitpoints(5)
                .build();
    }
    
    @Test
    public void testBuildNoHitpoints() {
        MonsterCard monsterCard = builder
                .name("Test")
                .attack(5)
                .maximumHitpoints(5)
                .modus(MonsterModus.HEALING)
                .build();
        
        assertEquals("Test", monsterCard.getName());
        assertEquals(5, monsterCard.getAttack());
        assertEquals(5, monsterCard.getMaximumHitpoints());
        assertEquals(5, monsterCard.getHitpoints());
        assertEquals(MonsterModus.HEALING, monsterCard.getModus());
    }
    
    @Test
    public void testBuildWithHitpointsLowerThanMaximumHitpoints() {
        MonsterCard monsterCard = builder
                .name("Test")
                .attack(5)
                .maximumHitpoints(5)
                .hitpoints(3)
                .modus(MonsterModus.HEALING)
                .build();
        
        assertEquals("Test", monsterCard.getName());
        assertEquals(5, monsterCard.getAttack());
        assertEquals(5, monsterCard.getMaximumHitpoints());
        assertEquals(3, monsterCard.getHitpoints());
        assertEquals(MonsterModus.HEALING, monsterCard.getModus());
    }
    
    @Test
    public void testBuildWithHitpointsEqualToMaximumHitpoints() {
        MonsterCard monsterCard = builder
                .name("Test")
                .attack(5)
                .maximumHitpoints(5)
                .hitpoints(5)
                .modus(MonsterModus.HEALING)
                .build();
        
        assertEquals("Test", monsterCard.getName());
        assertEquals(5, monsterCard.getAttack());
        assertEquals(5, monsterCard.getMaximumHitpoints());
        assertEquals(5, monsterCard.getHitpoints());
        assertEquals(MonsterModus.HEALING, monsterCard.getModus());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBuildWithHitpointsHigherThanMaximumHitpoints() {
        builder
                .name("Test")
                .attack(5)
                .maximumHitpoints(5)
                .hitpoints(6)
                .modus(MonsterModus.HEALING)
                .build();
    }
}
