
package com.skiwi.tcg.events;

import com.skiwi.tcg.model.objects.Hand;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class HandEventTest {
    static {
        assertTrue(true);
    }
    
    @Test
    public void testConstructor() {
        new HandEventImpl(new Hand(5));
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullHand() {
        new HandEventImpl(null);
    }

    @Test
    public void testGetHand() {
        Hand hand = new Hand(5);
        HandEvent handEvent = new HandEventImpl(hand);
        assertEquals(hand, handEvent.getHand());
    }

    public class HandEventImpl extends HandEvent {
        public HandEventImpl(final Hand hand) {
            super(hand);
        }
    }
}