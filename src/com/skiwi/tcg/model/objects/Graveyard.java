
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.Card;
import java.util.Collection;

/**
 *
 * @author Frank van Heeswijk
 */
public class Graveyard extends Pile {
    public Graveyard() {
        super();
    }
    
    public Graveyard(final Collection<? extends Card> cards) {
        super(cards);
    }
    
    public void transferAll(final Collection<? super Card> collection) {
        collection.addAll(this);
        clear();
    }
    
    @Override
    public String toString() {
        return Graveyard.class.getSimpleName() + "(" + size() + ")";
    }
}
