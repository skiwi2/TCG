
package com.skiwi.tcg.events;

import com.skiwi.tcg.model.objects.Hand;

/**
 *
 * @author Frank van Heeswijk
 */
public class HandSwappedEvent extends HandEvent {
    private final int indexOne;
    private final int indexTwo;
    
    public HandSwappedEvent(final Hand hand, final int indexOne, final int indexTwo) {
        super(hand);
        this.indexOne = indexOne;
        this.indexTwo = indexTwo;
    }
    
    public int getIndexOne() {
        return indexOne;
    }
    
    public int getIndexTwo() {
        return indexTwo;
    }
}
