
package com.skiwi.tcg.events;

import com.skiwi.tcg.model.objects.Hand;
import com.skiwi.tcg.utils.Arguments;

/**
 *
 * @author Frank van Heeswijk
 */
public class HandSwappedEvent extends HandEvent {
    private final int indexOne;
    private final int indexTwo;
    
    public HandSwappedEvent(final Hand hand, final int indexOne, final int indexTwo) {
        super(hand);
        this.indexOne = Arguments.requirePositiveOrZero(indexOne, "indexOne");
        this.indexTwo = Arguments.requirePositiveOrZero(indexTwo, "indexTwo");
    }
    
    public int getIndexOne() {
        return indexOne;
    }
    
    public int getIndexTwo() {
        return indexTwo;
    }
}
