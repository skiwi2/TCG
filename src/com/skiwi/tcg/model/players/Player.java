
package com.skiwi.tcg.model.players;

import com.skiwi.tcg.model.games.Game;
import com.skiwi.tcg.model.objects.Deck;
import com.skiwi.tcg.model.objects.Field;
import com.skiwi.tcg.model.objects.Graveyard;
import com.skiwi.tcg.model.objects.Hand;
import com.skiwi.tcg.utils.Arguments;
import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public class Player {
    private final String name;
    private final TurnAction turnAction;
    private final Hand hand;
    private final Field field;
    private final Deck deck;
    private final Graveyard graveyard;
    
    private int hitpoints;
    private Game game;
    
    private Status status = Status.NOT_CONSTRUCTED;
    
    public Player(final String name, final int hitpoints, final TurnAction turnAction, final Hand hand, final Field field, final Deck deck, final Graveyard graveyard) {
        Objects.requireNonNull(name);
        Arguments.requireMinimalLength(name, 1, "name");
        this.name = name;
        this.hitpoints = Arguments.requirePositive(hitpoints, "hitpoints");
        this.turnAction = Objects.requireNonNull(turnAction);
        this.hand = Objects.requireNonNull(hand);
        this.field = Objects.requireNonNull(field);
        this.deck = Objects.requireNonNull(deck);
        this.graveyard = Objects.requireNonNull(graveyard);
    }
    
    public static Player createFromConfiguration(final PlayerConfiguration playerConfiguration, final String name) {
        Objects.requireNonNull(playerConfiguration, "playerConfiguration");
        Objects.requireNonNull(name, "name");
        return new Player(
                name,
                playerConfiguration.getHitpoints(),
                playerConfiguration.getTurnAction(),
                new Hand(playerConfiguration.getHandCapacity()), 
                new Field(playerConfiguration.getFieldMonsterCapacity()), 
                Deck.newShuffledDeck(playerConfiguration.getDeckCards()),
                new Graveyard()
        );
    }
    
    public void setGame(final Game game) {
        if (status == Status.CONSTRUCTED) {
            throw new IllegalStateException("this player has already been fully constructed");
        }
        this.game = Objects.requireNonNull(game);
        status = Status.CONSTRUCTED;
    }
    
    public void playTurn() {
        assertConstructed();
        turnAction.performTurn(this);
    }
    
    private void assertConstructed() {
        if (status == Status.NOT_CONSTRUCTED) {
            throw new IllegalStateException("this player has not been fully constructed yet");
        }
    }
    
    public int increaseHitpoints(final int increment) {
        Arguments.requirePositiveOrZero(increment, "increment");
        int oldHitpoints = hitpoints;
        hitpoints += increment;
        return hitpoints - oldHitpoints;
    }
    
    public int decreaseHitpoints(final int decrement) {
        Arguments.requirePositiveOrZero(decrement, "decrement");
        int oldHitpoints = hitpoints;
        hitpoints = (hitpoints - decrement <= 0) ? 0 : hitpoints - decrement;
        return oldHitpoints - hitpoints;
    }
    
    public Game getGame() {
        assertConstructed();
        return game;
    }
    
    public String getName() {
        return name;
    }
    
    public Hand getHand() {
        return hand;
    }
    
    public Field getField() {
        return field;
    }
    
    public Deck getDeck() {
        return deck;
    }
    
    public Graveyard getGraveyard() {
        return graveyard;
    }
    
    public int getHitpoints() {
        return hitpoints;
    }
    
    public boolean isDead() {
        return (hitpoints == 0);
    }
    
    public Player getOpponent() {
        assertConstructed();
        return game.getOpponentFor(this).orElseThrow(() -> new IllegalStateException("No opponent has been found"));
    }
    
    @Override
    public String toString() {
        return "Player(" + name + ", " + hitpoints + ")";
    }
    
    private static enum Status {
        NOT_CONSTRUCTED, CONSTRUCTED;
    }
}
