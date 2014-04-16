
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.utils.Checker;
import java.util.Collection;

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
    
    public Card take() {
        Checker.checkElement(!isEmpty(), "deck should not be empty");
        return take(0);
    }
    
    @Override
    public String toString() {
        return "Deck(" + size() + ")";
    }
}
