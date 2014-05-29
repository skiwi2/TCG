
package com.skiwi.tcg.model.players.playeractions;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.cards.MonsterCard;
import com.skiwi.tcg.model.objects.Deck;
import com.skiwi.tcg.model.objects.Field;
import com.skiwi.tcg.model.objects.Graveyard;
import com.skiwi.tcg.model.objects.Hand;
import com.skiwi.tcg.model.objects.MonsterModus;
import com.skiwi.tcg.model.players.Player;
import com.skiwi.tcg.model.players.PlayerActionNotAllowedException;
import com.skiwi.tcg.model.players.TurnAction;
import java.util.Collections;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class DrawCardActionTest {
    private final DrawCardAction drawCardAction = new DrawCardAction();
    
    static {
        assertTrue(true);
    }

    @Test
    public void testIsActionAllowed() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(5), new Field(5), createDeck(20), new Graveyard());
        assertTrue(drawCardAction.isActionAllowed(player));
    }

    @Test
    public void testPerformAction() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(5), new Field(5), createDeck(1), new Graveyard());
        int handSize = player.getHand().size();
        int deckSize = player.getDeck().size();
        drawCardAction.performAction(player);
        assertEquals(handSize + 1, player.getHand().size());
        assertEquals(deckSize - 1, player.getDeck().size());
    }
    
    @Test(expected = NullPointerException.class)
    public void testIsActionAllowedNullPlayer() {
        drawCardAction.isActionAllowed(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void testPerformActionNullPlayer() {
        drawCardAction.performAction(null);
    }
    
    @Test
    public void testIsActionAllowedEmptyDeckNonEmptyGraveyard() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(5), new Field(5), new Deck(), createGraveyard(1));
        assertTrue(drawCardAction.isActionAllowed(player));
    }
    
    @Test
    public void testPerformActionEmptyDeckNonEmptyGraveyard() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(5), new Field(5), new Deck(), createGraveyard(1));
        int handSize = player.getHand().size();
        int graveyardSize = player.getGraveyard().size();
        drawCardAction.performAction(player);
        assertEquals(handSize + 1, player.getHand().size());
        assertEquals(graveyardSize - 1, player.getDeck().size());
        assertEquals(0, player.getGraveyard().size());
    }
    
    @Test
    public void testPerformActionDrawsTopOfDeck() {
        Deck deck = new Deck();
        Card cardOne = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        Card cardTwo = new MonsterCard("Test 2", 7, 7, MonsterModus.OFFENSIVE);
        deck.add(cardOne);
        deck.add(cardTwo);
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(5), new Field(5), deck, new Graveyard());
        Card cardOnTop = deck.get(0);
        drawCardAction.performAction(player);
        assertEquals(cardOnTop, player.getHand().get(0));
    }
    
    @Test
    public void testIsActionAllowedEmptyDeckAndGraveyard() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(5), new Field(5), new Deck(), new Graveyard());
        assertFalse(drawCardAction.isActionAllowed(player));
    }
    
    @Test(expected = PlayerActionNotAllowedException.class)
    public void testPerformActionEmptyDeckAndGraveyardThrowsIllegalStateException() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(5), new Field(5), new Deck(), new Graveyard());
        drawCardAction.performAction(player);
    }
    
    @Test
    public void testIsActionAllowedFullHand() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(1), new Field(5), createDeck(20), new Graveyard());
        player.getHand().add(createCard());
        assertFalse(drawCardAction.isActionAllowed(player));
    }
    
    @Test(expected = PlayerActionNotAllowedException.class)
    public void testPerformActionFullHand() {
        Player player = new Player("Test", 100, new TurnActionImpl(), new Hand(1), new Field(5), createDeck(20), new Graveyard());
        player.getHand().add(createCard());
        drawCardAction.performAction(player);
    }
    
    private Card createCard() {
        return new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
    }
    
    private Deck createDeck(final int size) {
        return Deck.newShuffledDeck(Collections.nCopies(size, createCard()));
    }
    
    private Graveyard createGraveyard(final int size) {
        return new Graveyard(Collections.nCopies(size, createCard()));
    }
    
    private static class TurnActionImpl implements TurnAction {
        @Override
        public void performTurn(final Player player) { }
    }
}