
package com.skiwi.tcg.model.players.playeractions;

import com.skiwi.tcg.model.cards.MonsterCard;
import com.skiwi.tcg.model.objects.MonsterModus;
import com.skiwi.tcg.model.players.Player;
import com.skiwi.tcg.model.players.PlayerAction;
import com.skiwi.tcg.model.players.PlayerActionAbstractTest;
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
public class AttackMonsterActionTest extends PlayerActionAbstractTest {
    static {
        assertTrue(true);
    }
    
    private Player self;
    private Player opponent;
    
    private final static PlayerConfiguration PLAYER_CONFIGURATION = new PlayerConfigurationBuilder()
            .hitpoints(100)
            .turnAction(p -> { })
            .handCapacity(5)
            .fieldMonsterCapacity(5)
            .deckCards(Arrays.asList(new MonsterCard("Random", 5, 5, MonsterModus.HEALING)))
            .build();
    
    @Override
    protected PlayerAction supplyPlayerAction() {
        return new AttackMonsterAction(0, 0, Player.createFromConfiguration(PLAYER_CONFIGURATION, "Opponent"));
    }
    
    @Before
    public void before() {
        self = Player.createFromConfiguration(PLAYER_CONFIGURATION, "Self");
        opponent = Player.createFromConfiguration(PLAYER_CONFIGURATION, "Opponent");
    }
    
    @Test
    public void testConstructor() {
        new AttackMonsterAction(0, 0, opponent);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeMonsterIndex() {
        new AttackMonsterAction(-1, 0, opponent);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeTargetMonsterIndex() {
        new AttackMonsterAction(0, -1, opponent);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorTooHighTargetMonsterIndex() {
        new AttackMonsterAction(0, 5, opponent);
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullPlayer() {
        new AttackMonsterAction(0, 0, null);
    }
    
    @Test
    public void testIsActionAllowedMonsterSurvives() {
        MonsterCard selfMonster = new MonsterCard("Test", 5, 20, MonsterModus.OFFENSIVE);
        MonsterCard targetMonster = new MonsterCard("Test Target", 10, 10, MonsterModus.HEALING);
        self.getField().setMonster(0, selfMonster);
        opponent.getField().setMonster(0, targetMonster);
        AttackMonsterAction attackMonsterAction = new AttackMonsterAction(0, 0, opponent);
        assertTrue(attackMonsterAction.isActionAllowed(self));
    }
    
    @Test
    public void testPerformActionMonsterSurvives() {
        MonsterCard selfMonster = new MonsterCard("Test", 5, 20, MonsterModus.OFFENSIVE);
        MonsterCard targetMonster = new MonsterCard("Test Target", 10, 10, MonsterModus.HEALING);
        self.getField().setMonster(0, selfMonster);
        opponent.getField().setMonster(0, targetMonster);
        AttackMonsterAction attackMonsterAction = new AttackMonsterAction(0, 0, opponent);
        attackMonsterAction.performAction(self);
        
        assertEquals(20, selfMonster.getHitpoints());
        assertEquals(MonsterModus.OFFENSIVE, selfMonster.getModus());
        
        assertEquals(5, targetMonster.getHitpoints());
        assertEquals(MonsterModus.HEALING, targetMonster.getModus());
        
        assertEquals(selfMonster, self.getField().getMonster(0));
        assertEquals(targetMonster, opponent.getField().getMonster(0));
    }
    
    @Test
    public void testIsActionAllowedMonsterDies() {
        MonsterCard selfMonster = new MonsterCard("Test", 50, 20, MonsterModus.OFFENSIVE);
        MonsterCard targetMonster = new MonsterCard("Test Target", 10, 10, MonsterModus.HEALING);
        self.getField().setMonster(0, selfMonster);
        opponent.getField().setMonster(0, targetMonster);
        AttackMonsterAction attackMonsterAction = new AttackMonsterAction(0, 0, opponent);
        assertTrue(attackMonsterAction.isActionAllowed(self));
    }
    
    @Test
    public void testPerformActionMonsterDies() {
        MonsterCard selfMonster = new MonsterCard("Test", 50, 20, MonsterModus.OFFENSIVE);
        MonsterCard targetMonster = new MonsterCard("Test Target", 10, 10, MonsterModus.HEALING);
        self.getField().setMonster(0, selfMonster);
        opponent.getField().setMonster(0, targetMonster);
        AttackMonsterAction attackMonsterAction = new AttackMonsterAction(0, 0, opponent);
        attackMonsterAction.performAction(self);
        
        assertEquals(20, selfMonster.getHitpoints());
        assertEquals(MonsterModus.OFFENSIVE, selfMonster.getModus());
        
        assertEquals(0, targetMonster.getHitpoints());
        
        assertEquals(selfMonster, self.getField().getMonster(0));
        assertFalse(opponent.getField().hasMonster(0));
    }
    
    @Test
    public void testIsActionAllowedTooHighMonsterIndex() {
        AttackMonsterAction attackMonsterAction = new AttackMonsterAction(5, 0, opponent);
        assertFalse(attackMonsterAction.isActionAllowed(self));
    }
    
    @Test(expected = PlayerActionNotAllowedException.class)
    public void testPerformActionTooHighMonsterIndex() {
        AttackMonsterAction attackMonsterAction = new AttackMonsterAction(5, 0, opponent);
        attackMonsterAction.performAction(self);
    }
    
    @Test
    public void testIsActionAllowedMonsterNotOnField() {
        AttackMonsterAction attackMonsterAction = new AttackMonsterAction(0, 0, opponent);
        opponent.getField().setMonster(0, new MonsterCard("Test", 5, 5, MonsterModus.OFFENSIVE));
        assertFalse(attackMonsterAction.isActionAllowed(self));
    }
    
    @Test(expected = PlayerActionNotAllowedException.class)
    public void testPerformActionMonsterNotOnField() {
        AttackMonsterAction attackMonsterAction = new AttackMonsterAction(0, 0, opponent);
        opponent.getField().setMonster(0, new MonsterCard("Test", 5, 5, MonsterModus.OFFENSIVE));
        attackMonsterAction.performAction(self);
    }
    
    @Test
    public void testIsActionAllowedTargetMonsterNotOnField() {
        AttackMonsterAction attackMonsterAction = new AttackMonsterAction(0, 0, opponent);
        self.getField().setMonster(0, new MonsterCard("Test", 5, 5, MonsterModus.OFFENSIVE));
        assertFalse(attackMonsterAction.isActionAllowed(self));
    }
    
    @Test(expected = PlayerActionNotAllowedException.class)
    public void testPerformActionTargetMonsterNotOnField() {
        AttackMonsterAction attackMonsterAction = new AttackMonsterAction(0, 0, opponent);
        self.getField().setMonster(0, new MonsterCard("Test", 5, 5, MonsterModus.OFFENSIVE));
        attackMonsterAction.performAction(self);
    }
    
    @Test
    public void testIsActionAllowedMonsterNotInOffensiveModus() {
        AttackMonsterAction attackMonsterAction = new AttackMonsterAction(0, 0, opponent);
        self.getField().setMonster(0, new MonsterCard("Test", 5, 5, MonsterModus.HEALING));
        opponent.getField().setMonster(0, new MonsterCard("Test", 5, 5, MonsterModus.HEALING));
        assertFalse(attackMonsterAction.isActionAllowed(self));
    }
    
    @Test(expected = PlayerActionNotAllowedException.class)
    public void testPerformActionMonsterNotInOffensiveModus() {
        AttackMonsterAction attackMonsterAction = new AttackMonsterAction(0, 0, opponent);
        self.getField().setMonster(0, new MonsterCard("Test", 5, 5, MonsterModus.HEALING));
        opponent.getField().setMonster(0, new MonsterCard("Test", 5, 5, MonsterModus.HEALING));
        attackMonsterAction.performAction(self);
    }
}