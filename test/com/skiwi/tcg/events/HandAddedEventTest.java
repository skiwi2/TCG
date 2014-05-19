
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
public class HandAddedEventTest {
    static {
        assertTrue(true);
    }

    @Test
    public void testConstructor() {
        new HandAddedEvent(new Hand(5), new MonsterCard("Test", 5, 5, MonsterModus.HEALING));
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullHand() {
        new HandAddedEvent(null, new MonsterCard("Test", 5, 5, MonsterModus.HEALING));
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullCard() {
        new HandAddedEvent(new Hand(5), null);
    }
    
    @Test
    public void testGetHand() {
        Hand hand = new Hand(5);
        Card card = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        HandAddedEvent handAddedEvent = new HandAddedEvent(hand, card);
        assertEquals(hand, handAddedEvent.getHand());
    }
    
    @Test
    public void testGetCard() {
        Hand hand = new Hand(5);
        Card card = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        HandAddedEvent handAddedEvent = new HandAddedEvent(hand, card);
        assertEquals(card, handAddedEvent.getCard());
    }
}