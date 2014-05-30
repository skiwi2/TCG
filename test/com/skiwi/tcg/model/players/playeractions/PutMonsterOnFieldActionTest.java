
package com.skiwi.tcg.model.players.playeractions;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.cards.FusionCard;
import com.skiwi.tcg.model.cards.MonsterCard;
import com.skiwi.tcg.model.objects.Deck;
import com.skiwi.tcg.model.objects.Field;
import com.skiwi.tcg.model.objects.FusionStat;
import com.skiwi.tcg.model.objects.Graveyard;
import com.skiwi.tcg.model.objects.Hand;
import com.skiwi.tcg.model.objects.MonsterModus;
import com.skiwi.tcg.model.players.Player;
import com.skiwi.tcg.model.players.PlayerActionAbstractTest;
import com.skiwi.tcg.model.players.PlayerActionNotAllowedException;
import com.skiwi.tcg.model.players.TurnAction;
import java.util.Collections;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class PutMonsterOnFieldActionTest extends PlayerActionAbstractTest {
    static {
        assertTrue(true);
    }
    
    public PutMonsterOnFieldActionTest() {
        super(() -> new PutMonsterOnFieldAction(0, 0));
    }
    
    @Test
    public void testConstructor() {
        new PutMonsterOnFieldAction(0, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeHandCardIndex() {
        new PutMonsterOnFieldAction(-1, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNgativeFieldMonsterIndex() {
        new PutMonsterOnFieldAction(0, -1);
    }

    @Test
    public void testIsActionAllowed() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(5), new Field(5), createDeck(10), new Graveyard());
        player.getHand().add(createCard());
        PutMonsterOnFieldAction putMonsterOnFieldAction = new PutMonsterOnFieldAction(0, 0);
        assertTrue(putMonsterOnFieldAction.isActionAllowed(player));
    }

    @Test
    public void testPerformAction() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(5), new Field(5), createDeck(10), new Graveyard());
        Hand hand = player.getHand();
        Field field = player.getField();
        Card card = createCard();
        Card card2 = createCard();
        assertNotSame(card, card2);
        hand.add(card);
        hand.add(card2);
        
        PutMonsterOnFieldAction putMonsterOnFieldAction = new PutMonsterOnFieldAction(0, 0);
        putMonsterOnFieldAction.performAction(player);
        assertEquals(1, hand.size());
        assertFalse(hand.contains(card));
        assertEquals(card2, hand.get(0));
        
        assertEquals(card, field.getMonster(0));
        assertEquals(1, field.getMonsters().count());
    }
    
    @Test
    public void testIsActionAllowedHandCardIndexOverCapacity() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(5), new Field(5), createDeck(10), new Graveyard());
        PutMonsterOnFieldAction putMonsterOnFieldAction = new PutMonsterOnFieldAction(5, 0);
        assertFalse(putMonsterOnFieldAction.isActionAllowed(player));
    }
    
    @Test(expected = PlayerActionNotAllowedException.class)
    public void testPerformActionHandCardIndexOverCapacity() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(5), new Field(5), createDeck(10), new Graveyard());
        PutMonsterOnFieldAction putMonsterOnFieldAction = new PutMonsterOnFieldAction(5, 0);
        putMonsterOnFieldAction.performAction(player);
    }
    
    @Test
    public void testIsActionAllowedFieldMonsterIndexOverCapacity() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(5), new Field(5), createDeck(10), new Graveyard());
        PutMonsterOnFieldAction putMonsterOnFieldAction = new PutMonsterOnFieldAction(0, 5);
        assertFalse(putMonsterOnFieldAction.isActionAllowed(player));
    }
    
    @Test(expected = PlayerActionNotAllowedException.class)
    public void testPerformActionFieldMonsterIndexOverCapacity() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(5), new Field(5), createDeck(10), new Graveyard());
        PutMonsterOnFieldAction putMonsterOnFieldAction = new PutMonsterOnFieldAction(0, 5);
        putMonsterOnFieldAction.performAction(player);
    }
    
    @Test
    public void testIsActionAllowedMonsterAlreadyExistsOnField() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(5), new Field(5), createDeck(10), new Graveyard());
        player.getHand().add(createCard());
        player.getField().setMonster(0, new MonsterCard("Test", 5, 5, MonsterModus.HEALING));
        PutMonsterOnFieldAction putMonsterOnFieldAction = new PutMonsterOnFieldAction(0, 0);
        assertFalse(putMonsterOnFieldAction.isActionAllowed(player));
    }
    
    @Test(expected = PlayerActionNotAllowedException.class)
    public void testPerformActionMonsterAlreadyExistsOnField() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(5), new Field(5), createDeck(10), new Graveyard());
        player.getHand().add(createCard());
        player.getField().setMonster(0, new MonsterCard("Test", 5, 5, MonsterModus.HEALING));
        PutMonsterOnFieldAction putMonsterOnFieldAction = new PutMonsterOnFieldAction(0, 0);
        putMonsterOnFieldAction.performAction(player);
    }
    
    @Test
    public void testIsActionAllowedNoMonsterCardSelected() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(5), new Field(5), createDeck(10), new Graveyard());
        player.getHand().add(new FusionCard("Test", 20, FusionStat.ATTACK));
        PutMonsterOnFieldAction putMonsterOnFieldAction = new PutMonsterOnFieldAction(0, 0);
        assertFalse(putMonsterOnFieldAction.isActionAllowed(player));
    }
    
    @Test(expected = PlayerActionNotAllowedException.class)
    public void testPerformActionNoMonsterCardSelected() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(5), new Field(5), createDeck(10), new Graveyard());
        player.getHand().add(new FusionCard("Test", 20, FusionStat.ATTACK));
        PutMonsterOnFieldAction putMonsterOnFieldAction = new PutMonsterOnFieldAction(0, 0);
        putMonsterOnFieldAction.performAction(player);
    }
    
    private Card createCard() {
        return new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
    }
    
    private Deck createDeck(final int size) {
        return Deck.newShuffledDeck(Collections.nCopies(size, createCard()));
    }
    
    private static class TurnActionImpl implements TurnAction {
        @Override
        public void performTurn(final Player player) { }
    }
}