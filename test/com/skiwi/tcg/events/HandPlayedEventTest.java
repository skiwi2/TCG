
package com.skiwi.tcg.events;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.cards.MonsterCard;
import com.skiwi.tcg.model.objects.Hand;
import com.skiwi.tcg.model.objects.MonsterModus;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class HandPlayedEventTest {
    static {
        assertTrue(true);
    }

    @Test
    public void testConstructor() {
        new HandPlayedEvent(new Hand(5), 0, new MonsterCard("Test", 5, 5, MonsterModus.HEALING));
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullHand() {
        new HandPlayedEvent(null, 0, new MonsterCard("Test", 5, 5, MonsterModus.HEALING));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeIndex() {
        new HandPlayedEvent(new Hand(5), -1, new MonsterCard("Test", 5, 5, MonsterModus.HEALING));
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullCard() {
        new HandPlayedEvent(new Hand(5), 0, null);
    }
    
    @Test
    public void testGetHand() {
        Hand hand = new Hand(5);
        Card card = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        HandPlayedEvent handPlayedEvent = new HandPlayedEvent(hand, 1, card);
        assertEquals(hand, handPlayedEvent.getHand());
    }
    
    @Test
    public void testGetIndex() {
        Hand hand = new Hand(5);
        Card card = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        HandPlayedEvent handPlayedEvent = new HandPlayedEvent(hand, 1, card);
        assertEquals(1, handPlayedEvent.getIndex());
    }
    
    @Test
    public void testGetCard() {
        Hand hand = new Hand(5);
        Card card = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        HandPlayedEvent handPlayedEvent = new HandPlayedEvent(hand, 1, card);
        assertEquals(card, handPlayedEvent.getCard());
    }
}