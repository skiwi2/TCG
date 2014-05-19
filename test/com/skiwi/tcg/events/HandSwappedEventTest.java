
package com.skiwi.tcg.events;

import com.skiwi.tcg.model.objects.Hand;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class HandSwappedEventTest {
    static {
        assertTrue(true);
    }

    @Test
    public void testConstructor() {
        new HandSwappedEvent(new Hand(5), 0, 0);
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullHand() {
        new HandSwappedEvent(null, 0, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeIndexOne() {
        new HandSwappedEvent(new Hand(5), -1, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeIndexTwo() {
        new HandSwappedEvent(new Hand(5), 0, -1);
    }
    
    @Test
    public void testGetHand() {
        Hand hand = new Hand(5);
        HandSwappedEvent handSwappedEvent = new HandSwappedEvent(hand, 1, 2);
        assertEquals(hand, handSwappedEvent.getHand());
    }
    
    @Test
    public void testGetIndexOne() {
        Hand hand = new Hand(5);
        HandSwappedEvent handSwappedEvent = new HandSwappedEvent(hand, 1, 2);
        assertEquals(1, handSwappedEvent.getIndexOne());
    }
    
    @Test
    public void testGetIndexTwo() {
        Hand hand = new Hand(5);
        HandSwappedEvent handSwappedEvent = new HandSwappedEvent(hand, 1, 2);
        assertEquals(2, handSwappedEvent.getIndexTwo());
    }
}