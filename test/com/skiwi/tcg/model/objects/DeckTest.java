
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.cards.MonsterCard;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class DeckTest {
    static {
        assertTrue(true);
    }
    
    @Test
    public void testConstructor() {
        new Deck();
    }
    
    @Test
    public void testConstructor_collection() {
        new Deck(createCollection());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructor_collectionNPE() {
        new Deck(null);
    }

    @Test
    public void testTake() {
        Deck deck = new Deck();
        Card card = createCard();
        Card card2 = createCard2();
        assertNotSame("card should be unequal to card2", card, card2);
        deck.add(card);
        deck.add(card2);
        assertEquals("top card should be card2", card2, deck.take());
        assertEquals("top card should now be card", card, deck.take());
        assertTrue(deck.isEmpty());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testTakeNSE() {
        Deck deck = new Deck();
        deck.take();
    }
    
    @Test
    public void testToString() {
        Deck deck = new Deck();
        deck.addAll(createCollection());
        assertEquals("Deck(2)", deck.toString());
    }
    
    private Card createCard() {
        return new MonsterCard("Test", 10, 100, MonsterModus.OFFENSIVE);
    }

    private Card createCard2() {
        return new MonsterCard("Test2", 15, 150, MonsterModus.HEALING);
    }
    
    private Collection<Card> createCollection() {
        Collection<Card> collection = new ArrayList<>();
        collection.add(createCard());
        collection.add(createCard2());
        return collection;
    }
}
