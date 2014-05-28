
package com.skiwi.tcg.model.players;

import com.skiwi.tcg.model.cards.Card;
import java.util.Collection;
import java.util.Optional;
import java.util.OptionalInt;

/**
 *
 * @author Frank van Heeswijk
 */
public class PlayerConfigurationBuilder {
    private OptionalInt hitpoints = OptionalInt.empty();
    private Optional<TurnAction> turnAction = Optional.empty();
    private OptionalInt handCapacity = OptionalInt.empty();
    private OptionalInt fieldMonsterCapacity = OptionalInt.empty();
    private Optional<Collection<Card>> deckCards = Optional.empty();
    
    public PlayerConfigurationBuilder hitpoints(final int hitpoints) {
        this.hitpoints = OptionalInt.of(hitpoints);
        return this;
    }
    
    public PlayerConfigurationBuilder turnAction(final TurnAction turnAction) {
        this.turnAction = Optional.of(turnAction);
        return this;
    }
    
    public PlayerConfigurationBuilder handCapacity(final int handCapacity) {
        this.handCapacity = OptionalInt.of(handCapacity);
        return this;
    }
    
    public PlayerConfigurationBuilder fieldMonsterCapacity(final int fieldMonsterCapacity) {
        this.fieldMonsterCapacity = OptionalInt.of(fieldMonsterCapacity);
        return this;
    }
    
    public PlayerConfigurationBuilder deckCards(final Collection<Card> deckCards) {
        this.deckCards = Optional.of(deckCards);
        return this;
    }
    
    public PlayerConfiguration build() {
        if (!hitpoints.isPresent() || !turnAction.isPresent() || !handCapacity.isPresent() || !fieldMonsterCapacity.isPresent() || !deckCards.isPresent()) {
            throw new IllegalStateException("The fields hitpoints, turnAction, handCapacity, fieldMonsterCapacity and deckCards are required, but not all are present.");
        }
        return new PlayerConfiguration(hitpoints.getAsInt(), turnAction.get(), handCapacity.getAsInt(), fieldMonsterCapacity.getAsInt(), deckCards.get());
    }
}
