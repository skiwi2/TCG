
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.cards.MonsterCard;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    public void testDeckConstructor() {
        new Deck();
    }
    
    @Test
    public void testDeckConstructor_collection() {
        new Deck(createCollection());
    }
    
    @Test(expected = NullPointerException.class)
    public void testDeckConstructor_collectionNPE() {
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
        assertEquals("top card should be card2", card2, deck.take().get());
        assertEquals("top card should now be card", card, deck.take().get());
        assertTrue(deck.isEmpty());
    }
    
    @Test
    public void testTakeEmpty() {
        Deck deck = new Deck();
        assertFalse(deck.take().isPresent());
    }
    
    @Test
    public void testTakeFirstSimilarToTake() {
        Card cardTakeFirst;
        Card cardTake;
        
        Card card = createCard();
        Card card2 = createCard2();
        assertNotSame(card, card2);
        
        Deck deck = new Deck();
        deck.add(card);
        deck.add(card2);
        cardTakeFirst = deck.take(0);
        
        Deck deck2 = new Deck();
        deck2.add(card);
        deck2.add(card2);
        cardTake = deck2.take().get();
        
        assertEquals("take first and take should be equal", cardTakeFirst, cardTake);
    }
    
    @Test
    public void testNewShuffledDeck() {
        Card card = createCard();
        Card card2 = createCard2();
        List<Card> list = new ArrayList<>();
        list.add(card);
        list.add(card2);
        assertNotSame(card, card2);
        Deck deck = Deck.newShuffledDeck(list);
        assertEquals(2, deck.size());
        assertTrue(deck.contains(card));
        assertTrue(deck.contains(card2));
    }
    
    @Test(expected = NullPointerException.class)
    public void testNewShuffledDeckNullCollection() {
        Deck.newShuffledDeck(null);
    }
    
    @Test
    public void testToString() {
        Deck deck = new Deck();
        deck.addAll(createCollection());
        assertEquals(Deck.class.getSimpleName() + "(2)", deck.toString());
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
