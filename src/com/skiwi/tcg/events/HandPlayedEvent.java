
package com.skiwi.tcg.events;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.objects.Hand;
import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public class HandPlayedEvent extends HandEvent {
    private final int index;
    private final Card card;
    
    public HandPlayedEvent(final Hand hand, final int index, final Card card) {
        super(hand);
        this.index = index;
        this.card = Objects.requireNonNull(card);
    }
    
    public int getIndex() {
        return index;
    }
    
    public Card getCard() {
        return card;
    }
}
