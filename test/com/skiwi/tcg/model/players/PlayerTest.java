
package com.skiwi.tcg.model.players;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.cards.MonsterCard;
import com.skiwi.tcg.model.games.Game;
import com.skiwi.tcg.model.objects.Deck;
import com.skiwi.tcg.model.objects.Field;
import com.skiwi.tcg.model.objects.Graveyard;
import com.skiwi.tcg.model.objects.Hand;
import com.skiwi.tcg.model.objects.MonsterModus;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class PlayerTest {
    static {
        assertTrue(true);
    }

    @Test
    public void testConstructor() {
        Hand hand = new Hand(5);
        Field field = new Field(5);
        Deck deck = new Deck();
        Graveyard graveyard = new Graveyard();
        Player player = new Player("Test", new TurnActionImpl(), hand, field, deck, graveyard);
        assertEquals("Test", player.getName());
        assertEquals(hand, player.getHand());
        assertEquals(field, player.getField());
        assertEquals(deck, player.getDeck());
        assertEquals(graveyard, player.getGraveyard());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullName() {
        new Player(null, new TurnActionImpl(), new Hand(5), new Field(5), new Deck(), new Graveyard());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEmptyName() {
        new Player("", new TurnActionImpl(), new Hand(5), new Field(5), new Deck(), new Graveyard());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullTurnAction() {
        new Player("Test", null, new Hand(5), new Field(5), new Deck(), new Graveyard());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullHand() {
        new Player("Test", new TurnActionImpl(), null, new Field(5), new Deck(), new Graveyard());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullField() {
        new Player("Test", new TurnActionImpl(), new Hand(5), null, new Deck(), new Graveyard());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullDeck() {
        new Player("Test", new TurnActionImpl(), new Hand(5), new Field(5), null, new Graveyard());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullGraveyard() {
        new Player("Test", new TurnActionImpl(), new Hand(5), new Field(5), new Deck(), null);
    }
    
    @Test
    public void testCreateFromConfiguration() {
        List<Card> cards = new ArrayList<>();
        cards.add(new MonsterCard("Test", 5, 5, MonsterModus.HEALING));
        PlayerConfiguration playerConfiguration = new PlayerConfigurationBuilder()
                .turnAction(new TurnActionImpl())
                .handCapacity(5)
                .fieldMonsterCapacity(5)
                .deckCards(cards)
                .build();
        Player player = Player.createFromConfiguration(playerConfiguration, "Test");
        assertEquals("Test", player.getName());
        assertEquals(5, player.getHand().getCapacity());
        assertEquals(5, player.getField().getMonsterCapacity());
        assertTrue(player.getDeck().containsAll(cards));
        assertTrue(player.getGraveyard().isEmpty());
    }
    
    @Test(expected = NullPointerException.class)
    public void testCreateFromConfigurationNullPlayerConfiguration() {
        Player.createFromConfiguration(null, "Test");
    }
    
    @Test(expected = NullPointerException.class)
    public void testCreateFromConfigurationNullName() {
        List<Card> cards = new ArrayList<>();
        cards.add(new MonsterCard("Test", 5, 5, MonsterModus.HEALING));
        PlayerConfiguration playerConfiguration = new PlayerConfigurationBuilder()
                .turnAction(new TurnActionImpl())
                .handCapacity(5)
                .fieldMonsterCapacity(5)
                .deckCards(cards)
                .build();
        Player.createFromConfiguration(playerConfiguration, null);
    }
    
    @Test
    public void testSetGame() {
        Player player = new Player("Test", new TurnActionImpl(), new Hand(5), new Field(5), new Deck(), new Graveyard());
        Game game = new Game();
        player.setGame(game);
        assertEquals(game, player.getGame());
    }
    
    @Test(expected = NullPointerException.class)
    public void testSetGameNullGame() {
        Player player = new Player("Test", new TurnActionImpl(), new Hand(5), new Field(5), new Deck(), new Graveyard());
        player.setGame(null);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testSetGameAlreadyConstructed() {
        Player player = new Player("Test", new TurnActionImpl(), new Hand(5), new Field(5), new Deck(), new Graveyard());
        Game game = new Game();
        player.setGame(game);
        player.setGame(game);
    }
    
    @Test
    public void testPlayTurn() {
        AtomicInteger counter = new AtomicInteger(0);
        Player player = new Player("Test", p -> counter.incrementAndGet(), new Hand(5), new Field(5), new Deck(), new Graveyard());
        player.setGame(new Game());
        player.playTurn();
        assertEquals(1, counter.get());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testPlayTurnNotConstructed() {
        Player player = new Player("Test", new TurnActionImpl(), new Hand(5), new Field(5), new Deck(), new Graveyard());
        player.playTurn();
    }
    
    @Test
    public void testGetGame() {
        Player player = new Player("Test", new TurnActionImpl(), new Hand(5), new Field(5), new Deck(), new Graveyard());
        Game game = new Game();
        player.setGame(game);
        assertEquals(game, player.getGame());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testGetGameNotConstructed() {
        Player player = new Player("Test", new TurnActionImpl(), new Hand(5), new Field(5), new Deck(), new Graveyard());
        player.getGame();
    }
    
    @Test
    public void testGetName() {
        Hand hand = new Hand(5);
        Field field = new Field(5);
        Deck deck = new Deck();
        Graveyard graveyard = new Graveyard();
        Player player = new Player("Test", new TurnActionImpl(), hand, field, deck, graveyard);
        assertEquals("Test", player.getName());
    }
    
    @Test
    public void testGetHand() {
        Hand hand = new Hand(5);
        Field field = new Field(5);
        Deck deck = new Deck();
        Graveyard graveyard = new Graveyard();
        Player player = new Player("Test", new TurnActionImpl(), hand, field, deck, graveyard);
        assertEquals(hand, player.getHand());
    }
    
    @Test
    public void testGetField() {
        Hand hand = new Hand(5);
        Field field = new Field(5);
        Deck deck = new Deck();
        Graveyard graveyard = new Graveyard();
        Player player = new Player("Test", new TurnActionImpl(), hand, field, deck, graveyard);
        assertEquals(field, player.getField());
    }
    
    @Test
    public void testGetDeck() {
        Hand hand = new Hand(5);
        Field field = new Field(5);
        Deck deck = new Deck();
        Graveyard graveyard = new Graveyard();
        Player player = new Player("Test", new TurnActionImpl(), hand, field, deck, graveyard);
        assertEquals(deck, player.getDeck());
    }
    
    @Test
    public void testGetGraveyard() {
        Hand hand = new Hand(5);
        Field field = new Field(5);
        Deck deck = new Deck();
        Graveyard graveyard = new Graveyard();
        Player player = new Player("Test", new TurnActionImpl(), hand, field, deck, graveyard);
        assertEquals(graveyard, player.getGraveyard());
    }
    
    @Test
    public void testGetOpponent() {
        List<Card> cards = new ArrayList<>();
        cards.add(new MonsterCard("Test", 5, 5, MonsterModus.HEALING));
        PlayerConfiguration playerConfiguration = new PlayerConfigurationBuilder()
                .turnAction(new TurnActionImpl())
                .handCapacity(5)
                .fieldMonsterCapacity(5)
                .deckCards(cards)
                .build();
        Player playerSelf = Player.createFromConfiguration(playerConfiguration, "Self");
        Player playerOpponent = Player.createFromConfiguration(playerConfiguration, "Opponent");
        Game game = new Game();
        game.setSelf(playerSelf);
        game.setOpponent(playerOpponent);
        assertEquals(playerOpponent, playerSelf.getOpponent());
        assertEquals(playerSelf, playerOpponent.getOpponent());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testGetOpponentNotConstructed() {
        Player player = new Player("Test", new TurnActionImpl(), new Hand(5), new Field(5), new Deck(), new Graveyard());
        player.getOpponent();
    }
    
    @Test
    public void testToString() {
        Player player = new Player("Test", new TurnActionImpl(), new Hand(5), new Field(5), new Deck(), new Graveyard());
        assertEquals("Player(Test)", player.toString());
    }
    
    private static class TurnActionImpl implements TurnAction {
        @Override
        public void performTurn(final Player player) {
            Objects.requireNonNull(player, "player");
        }
    }
}