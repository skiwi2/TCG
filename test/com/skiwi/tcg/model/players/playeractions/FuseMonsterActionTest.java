
package com.skiwi.tcg.model.players.playeractions;

import com.skiwi.tcg.model.cards.FusionCard;
import com.skiwi.tcg.model.cards.MonsterCard;
import com.skiwi.tcg.model.objects.Field;
import com.skiwi.tcg.model.objects.FusionStat;
import com.skiwi.tcg.model.objects.Graveyard;
import com.skiwi.tcg.model.objects.Hand;
import com.skiwi.tcg.model.objects.MonsterModus;
import com.skiwi.tcg.model.players.Player;
import com.skiwi.tcg.model.players.PlayerActionNotAllowedException;
import com.skiwi.tcg.model.players.PlayerConfiguration;
import com.skiwi.tcg.model.players.PlayerConfigurationBuilder;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class FuseMonsterActionTest {
    static {
        assertTrue(true);
    }
    
    private Player player;
    
    private final PlayerConfiguration playerConfiguration = new PlayerConfigurationBuilder()
            .hitpoints(100)
            .turnAction(p -> { })
            .handCapacity(5)
            .fieldMonsterCapacity(5)
            .deckCards(Arrays.asList(new MonsterCard("Random", 5, 5, MonsterModus.HEALING)))
            .build();
    
    @Before
    public void before() {
        player = Player.createFromConfiguration(playerConfiguration, "Test");
    }
    
    @Test
    public void testConstructor() {
        new FuseMonsterAction(0, 0, 1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeFusionCardIndex() {
        new FuseMonsterAction(-1, 0, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeBaseMonsterCardIndex() {
        new FuseMonsterAction(0, -1, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeFuserMonsterCardIndex() {
        new FuseMonsterAction(0, 0, -1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEqualBaseMonsterCardIndexAndFuserMonsterCardIndex() {
        new FuseMonsterAction(0, 0, 0);
    }
    
    @Test
    public void testIsActionAllowed() {
        Hand hand = player.getHand();
        Field field = player.getField();
        hand.add(new FusionCard("Fusion Card", 50, FusionStat.ATTACK));
        field.setMonster(0, new MonsterCard("Base Monster", 10, 50, MonsterModus.HEALING));
        field.setMonster(1, new MonsterCard("Fuser Monster", 40, 50, MonsterModus.HEALING));
        FuseMonsterAction fuseMonsterAction = new FuseMonsterAction(0, 0, 1);
        assertTrue(fuseMonsterAction.isActionAllowed(player));
    }
    
    @Test
    public void testPerformAction() {
        Hand hand = player.getHand();
        Field field = player.getField();
        Graveyard graveyard = player.getGraveyard();
        FusionCard fusionCard = new FusionCard("Fusion Card", 50, FusionStat.ATTACK);
        MonsterCard baseMonster = new MonsterCard("Base Monster", 10, 50, MonsterModus.HEALING);
        MonsterCard fuserMonster = new MonsterCard("Fuser Monster", 40, 50, MonsterModus.HEALING);
        hand.add(fusionCard);
        field.setMonster(0, baseMonster);
        field.setMonster(1, fuserMonster);
        FuseMonsterAction fuseMonsterAction = new FuseMonsterAction(0, 0, 1);
        
        fuseMonsterAction.performAction(player);
        assertFalse(hand.contains(fusionCard));
        assertFalse(field.hasMonster(1));
        assertTrue(field.hasMonster(0));
        assertNotSame(baseMonster, field.getMonster(0));
        assertNotSame(fuserMonster, field.getMonster(0));
        
        assertTrue(graveyard.contains(fusionCard));
        assertTrue(graveyard.contains(baseMonster));
        assertTrue(graveyard.contains(fuserMonster));
    }
    
    @Test(expected = NullPointerException.class)
    public void testIsActionAllowedNullPlayer() {
        FuseMonsterAction fuseMonsterAction = new FuseMonsterAction(0, 0, 1);
        fuseMonsterAction.isActionAllowed(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void testPerformActionNullPlayer() {
        FuseMonsterAction fuseMonsterAction = new FuseMonsterAction(0, 0, 1);
        fuseMonsterAction.performAction(null);
    }
    
    @Test
    public void testIsActionAllowedFusionCardIndexOverHandCapacity() {
        FuseMonsterAction fuseMonsterAction = new FuseMonsterAction(5, 0, 1);
        assertFalse(fuseMonsterAction.isActionAllowed(player));
    }
    
    @Test(expected = PlayerActionNotAllowedException.class)
    public void testPerformActionFusionCardIndexOverHandCapacity() {
        FuseMonsterAction fuseMonsterAction = new FuseMonsterAction(5, 0, 1);
        fuseMonsterAction.performAction(player);
    }
    
    @Test
    public void testIsActionAllowedBaseMonsterCardIndexOverFieldMonsterCapacity() {
        FuseMonsterAction fuseMonsterAction = new FuseMonsterAction(0, 5, 1);
        assertFalse(fuseMonsterAction.isActionAllowed(player));
    }
    
    @Test(expected = PlayerActionNotAllowedException.class)
    public void testPerformActionBaseMonsterCardIndexOverFieldMonsterCapacity() {
        FuseMonsterAction fuseMonsterAction = new FuseMonsterAction(0, 5, 1);
        fuseMonsterAction.performAction(player);
    }
    
    @Test
    public void testIsActionAllowedBaseFuserCardIndexOverFieldMonsterCapacity() {
        FuseMonsterAction fuseMonsterAction = new FuseMonsterAction(0, 0, 5);
        assertFalse(fuseMonsterAction.isActionAllowed(player));
    }
    
    @Test(expected = PlayerActionNotAllowedException.class)
    public void testPerformActionBaseFuserCardIndexOverFieldMonsterCapacity() {
        FuseMonsterAction fuseMonsterAction = new FuseMonsterAction(0, 0, 5);
        fuseMonsterAction.performAction(player);
    }
    
    @Test
    public void testIsActionAllowedNonFusionCardInHand() {
        FuseMonsterAction fuseMonsterAction = new FuseMonsterAction(0, 0, 1);
        player.getHand().add(new MonsterCard("Random", 5, 5, MonsterModus.HEALING));
        assertFalse(fuseMonsterAction.isActionAllowed(player));
    }
    
    @Test(expected = PlayerActionNotAllowedException.class)
    public void testPerformActionNonFusionCardInHand() {
        FuseMonsterAction fuseMonsterAction = new FuseMonsterAction(0, 0, 1);
        player.getHand().add(new MonsterCard("Random", 5, 5, MonsterModus.HEALING));
        fuseMonsterAction.performAction(player);
    }
    
    @Test
    public void testIsActionAllowedBaseMonsterCardNotOnField() {
        FuseMonsterAction fuseMonsterAction = new FuseMonsterAction(0, 0, 1);
        player.getHand().add(new FusionCard("Fusion Card", 50, FusionStat.ATTACK));
        assertFalse(fuseMonsterAction.isActionAllowed(player));
    }
    
    @Test(expected = PlayerActionNotAllowedException.class)
    public void testPerformActionBaseMonsterCardNotOnField() {
        FuseMonsterAction fuseMonsterAction = new FuseMonsterAction(0, 0, 1);
        player.getHand().add(new FusionCard("Fusion Card", 50, FusionStat.ATTACK));
        fuseMonsterAction.performAction(player);
    }
    
    @Test
    public void testIsActionAllowedFuserMonsterCardNotOnField() {
        FuseMonsterAction fuseMonsterAction = new FuseMonsterAction(0, 0, 1);
        player.getHand().add(new FusionCard("Fusion Card", 50, FusionStat.ATTACK));
        player.getField().setMonster(0, new MonsterCard("Base Monster", 5, 5, MonsterModus.HEALING));
        assertFalse(fuseMonsterAction.isActionAllowed(player));
    }
    
    @Test(expected = PlayerActionNotAllowedException.class)
    public void testPerformActionFuserMonsterCardNotOnField() {
        FuseMonsterAction fuseMonsterAction = new FuseMonsterAction(0, 0, 1);
        player.getHand().add(new FusionCard("Fusion Card", 50, FusionStat.ATTACK));
        player.getField().setMonster(0, new MonsterCard("Base Monster", 5, 5, MonsterModus.HEALING));
        fuseMonsterAction.performAction(player);
    }
}