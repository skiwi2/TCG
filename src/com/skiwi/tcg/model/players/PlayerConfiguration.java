
package com.skiwi.tcg.model.players;

import com.skiwi.tcg.model.cards.Card;
import java.util.Collection;
import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public class PlayerConfiguration {
    private final int hitpoints;
    private final TurnAction turnAction;
    private final int handCapacity;
    private final int fieldMonsterCapacity;
    private final Collection<Card> deckCards;
    
    public PlayerConfiguration(final int hitpoints, final TurnAction turnAction, final int handCapacity, final int fieldMonsterCapacity, final Collection<Card> deckCards) {
        this.hitpoints = hitpoints;
        this.turnAction = Objects.requireNonNull(turnAction, "turnAction");
        this.handCapacity = handCapacity;
        this.fieldMonsterCapacity = fieldMonsterCapacity;
        this.deckCards = Objects.requireNonNull(deckCards, "deckCards");
    }
    
    public int getHitpoints() {
        return hitpoints;
    }
    
    public TurnAction getTurnAction() {
        return turnAction;
    }
    
    public int getHandCapacity() {
        return handCapacity;
    }
    
    public int getFieldMonsterCapacity() {
        return fieldMonsterCapacity;
    }
    
    public Collection<Card> getDeckCards() {
        return deckCards;
    }
}
