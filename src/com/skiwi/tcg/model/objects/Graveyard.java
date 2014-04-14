
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.Card;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 *
 * @author Frank van Heeswijk
 */
public class Graveyard {
    private final Queue<Card> queue = new LinkedList<>();
    
    public Graveyard() { }
    
    public Graveyard(final Collection<Card> cards) {
        Objects.requireNonNull(cards);
        queue.addAll(cards);
    }
    
    public void add(final Card card) {
        Objects.requireNonNull(card);
        queue.add(card);
    }
    
    public void addAll(final Collection<Card> cards) {
        Objects.requireNonNull(cards);
        queue.addAll(cards);
    }
}
