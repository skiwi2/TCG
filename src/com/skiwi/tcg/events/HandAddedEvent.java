
package com.skiwi.tcg.events;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.objects.Hand;
import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public class HandAddedEvent extends HandEvent {
    private final Card card;
    
    public HandAddedEvent(final Hand hand, final Card addedCard) {
        super(hand);
        this.card = Objects.requireNonNull(addedCard);
    }
    
    public Card getCard() {
        return card;
    }
}
