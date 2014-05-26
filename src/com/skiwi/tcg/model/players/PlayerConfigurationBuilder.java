
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
    private Optional<TurnAction> turnAction = Optional.empty();
    private OptionalInt handCapacity = OptionalInt.empty();
    private OptionalInt fieldMonsterCapacity = OptionalInt.empty();
    private Optional<Collection<Card>> deckCards = Optional.empty();
    
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
        if (!turnAction.isPresent() || !handCapacity.isPresent() || !fieldMonsterCapacity.isPresent() || !deckCards.isPresent()) {
            throw new IllegalStateException("The fields turnAction, handCapacity, fieldMonsterCapacity and deckCards are required, but not all are present.");
        }
        return new PlayerConfiguration(turnAction.get(), handCapacity.getAsInt(), fieldMonsterCapacity.getAsInt(), deckCards.get());
    }
}
