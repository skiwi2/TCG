
package com.skiwi.tcg.events;

import com.skiwi.tcg.model.objects.Hand;
import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public abstract class HandEvent {
    private final Hand hand;
    
    public HandEvent(final Hand hand) {
        this.hand = Objects.requireNonNull(hand);
    }
    
    final public Hand getHand() {
        return hand;
    }
}
