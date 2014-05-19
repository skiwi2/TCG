
package com.skiwi.tcg.events;

import com.skiwi.tcg.model.objects.Hand;
import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public abstract class HandEvent {
    protected final Hand hand;
    
    public HandEvent(final Hand hand) {
        this.hand = Objects.requireNonNull(hand);
    }
    
    public Hand getHand() {
        return hand;
    }
}
