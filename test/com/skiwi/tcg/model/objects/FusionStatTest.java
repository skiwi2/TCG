
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.FusionCard;
import com.skiwi.tcg.model.cards.MonsterCard;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class FusionStatTest {
    static {
        assertTrue(true);
    }
    
    /** Preconditions test, only on one constant. **/
    
    @Test(expected = NullPointerException.class)
    public void testCreateBuilderNullBaseMonsterCard() {
        MonsterCard fusedMonsterCard = new MonsterCard("Fused", 20, 20, MonsterModus.OFFENSIVE);
        FusionCard fusionCard = new FusionCard("Fuser", 20, FusionStat.ATTACK);
        FusionStat.ATTACK.createBuilder(null, fusedMonsterCard, fusionCard);
    }
    
    @Test(expected = NullPointerException.class)
    public void testCreateBuilderNullFusedMonsterCard() {
        MonsterCard baseMonsterCard = new MonsterCard("Base", 10, 10, MonsterModus.HEALING);
        FusionCard fusionCard = new FusionCard("Fuser", 20, FusionStat.ATTACK);
        FusionStat.ATTACK.createBuilder(baseMonsterCard, null, fusionCard);
    }
    
    @Test(expected = NullPointerException.class)
    public void testCreateBuilderNullFusionCard() {
        MonsterCard baseMonsterCard = new MonsterCard("Base", 10, 10, MonsterModus.HEALING);
        MonsterCard fusedMonsterCard = new MonsterCard("Fused", 20, 20, MonsterModus.OFFENSIVE);
        FusionStat.ATTACK.createBuilder(baseMonsterCard, fusedMonsterCard, null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateBuilderSimilarBaseMonsterCardAndFusedMonsterCard() {
        MonsterCard monsterCard = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        FusionCard fusionCard = new FusionCard("Fuser", 20, FusionStat.ATTACK);
        FusionStat.ATTACK.createBuilder(monsterCard, monsterCard, fusionCard);
    }
    
    /** Tests per constant. **/

    @Test
    public void testAttackCreateBuilderIntegerResult() {
        MonsterCard baseMonsterCard = new MonsterCard("Base", 10, 10, MonsterModus.HEALING);
        MonsterCard fusedMonsterCard = new MonsterCard("Fused", 20, 20, MonsterModus.OFFENSIVE);
        FusionCard fusionCard = new FusionCard("Fuser", 20, FusionStat.ATTACK);
        MonsterCard builtCard = FusionStat.ATTACK.createBuilder(baseMonsterCard, fusedMonsterCard, fusionCard).build();
        
        assertEquals("Base", builtCard.getName());
        assertEquals(14, builtCard.getAttack());
        assertEquals(10, builtCard.getMaximumHitpoints());
        assertEquals(10, builtCard.getHitpoints());
        assertEquals(MonsterModus.HEALING, builtCard.getModus());
    }

    @Test
    public void testAttackCreateBuilderNonIntegerResult() {
        MonsterCard baseMonsterCard = new MonsterCard("Base", 10, 10, MonsterModus.HEALING);
        MonsterCard fusedMonsterCard = new MonsterCard("Fused", 20, 20, MonsterModus.OFFENSIVE);
        FusionCard fusionCard = new FusionCard("Fuser", 33, FusionStat.ATTACK);
        MonsterCard builtCard = FusionStat.ATTACK.createBuilder(baseMonsterCard, fusedMonsterCard, fusionCard).build();
        
        assertEquals("Base", builtCard.getName());
        assertEquals(16, builtCard.getAttack());
        assertEquals(10, builtCard.getMaximumHitpoints());
        assertEquals(10, builtCard.getHitpoints());
        assertEquals(MonsterModus.HEALING, builtCard.getModus());
    }
    
    @Test
    public void testHitpointsCreateBuilderFullHitpointsIntegerResult() {
        MonsterCard baseMonsterCard = new MonsterCard("Base", 10, 10, MonsterModus.HEALING);
        MonsterCard fusedMonsterCard = new MonsterCard("Fused", 20, 20, MonsterModus.OFFENSIVE);
        FusionCard fusionCard = new FusionCard("Fuser", 20, FusionStat.HITPOINTS);
        MonsterCard builtCard = FusionStat.HITPOINTS.createBuilder(baseMonsterCard, fusedMonsterCard, fusionCard).build();
        
        assertEquals("Base", builtCard.getName());
        assertEquals(10, builtCard.getAttack());
        assertEquals(14, builtCard.getMaximumHitpoints());
        assertEquals(14, builtCard.getHitpoints());
        assertEquals(MonsterModus.HEALING, builtCard.getModus());
    }

    @Test
    public void testHitpointsCreateBuilderFullHitpointsNonIntegerResult() {
        MonsterCard baseMonsterCard = new MonsterCard("Base", 10, 10, MonsterModus.HEALING);
        MonsterCard fusedMonsterCard = new MonsterCard("Fused", 20, 20, MonsterModus.OFFENSIVE);
        FusionCard fusionCard = new FusionCard("Fuser", 33, FusionStat.HITPOINTS);
        MonsterCard builtCard = FusionStat.HITPOINTS.createBuilder(baseMonsterCard, fusedMonsterCard, fusionCard).build();
        
        assertEquals("Base", builtCard.getName());
        assertEquals(10, builtCard.getAttack());
        assertEquals(16, builtCard.getMaximumHitpoints());
        assertEquals(16, builtCard.getHitpoints());
        assertEquals(MonsterModus.HEALING, builtCard.getModus());
    }
    
    @Test
    public void testHitpointsCreateBuilderNonFullHitpointsIntegerResult() {
        MonsterCard baseMonsterCard = new MonsterCard("Base", 10, 10, MonsterModus.HEALING);
        baseMonsterCard.decreaseHitpoints(5);
        MonsterCard fusedMonsterCard = new MonsterCard("Fused", 20, 20, MonsterModus.OFFENSIVE);
        FusionCard fusionCard = new FusionCard("Fuser", 20, FusionStat.HITPOINTS);
        MonsterCard builtCard = FusionStat.HITPOINTS.createBuilder(baseMonsterCard, fusedMonsterCard, fusionCard).build();
        
        assertEquals("Base", builtCard.getName());
        assertEquals(10, builtCard.getAttack());
        assertEquals(14, builtCard.getMaximumHitpoints());
        assertEquals(9, builtCard.getHitpoints());
        assertEquals(MonsterModus.HEALING, builtCard.getModus());
    }

    @Test
    public void testHitpointsCreateBuilderNonFullHitpointsNonIntegerResult() {
        MonsterCard baseMonsterCard = new MonsterCard("Base", 10, 10, MonsterModus.HEALING);
        baseMonsterCard.decreaseHitpoints(5);
        MonsterCard fusedMonsterCard = new MonsterCard("Fused", 20, 20, MonsterModus.OFFENSIVE);
        FusionCard fusionCard = new FusionCard("Fuser", 33, FusionStat.HITPOINTS);
        MonsterCard builtCard = FusionStat.HITPOINTS.createBuilder(baseMonsterCard, fusedMonsterCard, fusionCard).build();
        
        assertEquals("Base", builtCard.getName());
        assertEquals(10, builtCard.getAttack());
        assertEquals(16, builtCard.getMaximumHitpoints());
        assertEquals(11, builtCard.getHitpoints());
        assertEquals(MonsterModus.HEALING, builtCard.getModus());
    }
}