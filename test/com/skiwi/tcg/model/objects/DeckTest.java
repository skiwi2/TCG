
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.cards.MonsterCard;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Beheerder
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
    public void testAdd() {
        Deck deck = new Deck();
        assertTrue(deck.add(createCard()));
    }
    
    @Test(expected = NullPointerException.class)
    public void testAddNPE() {
        Deck deck = new Deck();
        deck.add(null);
    }

    @Test(timeout = 5000)
    public void testShuffle() {
        boolean shuffled = false;
        Card card = createCard();
        Card card2 = createCard2();
        assertNotSame("card should be unequal to card2", card, card2);
        Deck deck = new Deck();
        deck.add(card);
        deck.add(card2);
        assertEquals("expected card2 on top", card2, deck.get(0));
        while (!shuffled) {
            deck.shuffle();
            if (!deck.get(0).equals(card2)) {
                shuffled = true;    //card2 is not on top anymore
            }
        }
        assertEquals("now expecting card on top", card, deck.get(0));
    }
    
    @Test
    public void testGet() {
        Deck deck = new Deck();
        Card card = createCard();
        deck.add(card);
        assertEquals("card should be equals to obtained element", card, deck.get(0));
    }
    
    @Test
    public void testGet2() {
        Deck deck = new Deck();
        Card card = createCard();
        Card card2 = createCard2();
        deck.add(card);
        deck.add(card2);
        assertEquals("card should now be second card", card, deck.get(1));
        assertEquals("card2 should now be first card", card2, deck.get(0));
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIOOBE() {
        Deck deck = new Deck();
        deck.get(0);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIOOBE2() {
        Deck deck = new Deck();
        deck.get(-1);
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
    public void testTake_integer() {
        Deck deck = new Deck();
        Card card = createCard();
        Card card2 = createCard2();
        assertNotSame("card should be unequal to card2", card, card2);
        deck.add(card);
        deck.add(card2);
        assertEquals("second card should be card", card, deck.take(1));
        assertEquals("first card should be card2", card2, deck.take(0));
        assertTrue(deck.isEmpty());
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testTake_integerIOOBE() {
        Deck deck = new Deck();
        deck.take(0);
    }
    
    @Test
    public void testToString() {
        Deck deck = new Deck();
        deck.addAll(createCollection());
        assertEquals("Deck(2)", deck.toString());
    }
    
    @Test
    public void testIterator() {
        Deck deck = new Deck();
        Card card = createCard();
        Card card2 = createCard2();
        assertNotSame("card should be unequal to card2", card, card2);
        deck.add(card);
        deck.add(card2);
        Iterator<Card> iterator = deck.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("card on top should be card2", card2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("card below should be card", card, iterator.next());
        assertFalse(iterator.hasNext());
    }
    
    @Test
    public void testSpliterator() {
        assertNotNull(createFilledDeck().spliterator());
    }
    
    @Test
    public void testSize() {
        Deck deck = new Deck();
        assertEquals("empty deck", 0, deck.size());
        deck.add(createCard());
        assertEquals("deck with one card", 1, deck.size());
        deck.add(createCard2());
        assertEquals("deck with two cards", 2, deck.size());
    }
    
    @Test
    public void testForEach() {
        createFilledDeck().forEach(Assert::assertNotNull);
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
    
    private Deck createFilledDeck() {
        Deck deck = new Deck();
        Card card = createCard();
        Card card2 = createCard2();
        assertNotSame("card should be unequal to card2", card, card2);
        deck.add(card);
        deck.add(card2);
        return deck;
    }
}
