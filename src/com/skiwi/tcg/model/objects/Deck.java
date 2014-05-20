
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.Card;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author Frank van Heeswijk
 */
public class Deck extends Pile {
    public Deck() {
        super();
    }
    
    public Deck(final Collection<? extends Card> cards) {
        super(cards);
    }
    
    public Optional<Card> take() {
        if (isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(take(0));
    }
    
    public static Deck newShuffledDeck(final Collection<? extends Card> cards) {
        Deck deck = new Deck(Objects.requireNonNull(cards));
        deck.shuffle();
        return deck;
    }
    
    @Override
    public String toString() {
        return Deck.class.getSimpleName() + "(" + size() + ")";
    }
}
