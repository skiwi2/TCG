
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.cards.MonsterCard;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class PileTest {
    static {
        assertTrue(true);
    }
    
    @Test
    public void testConstructor() {
        new PileImpl();
    }
    
    @Test
    public void testConstructor_collection() {
        new PileImpl(createCollection());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructor_collectionNPE() {
        new PileImpl(null);
    }

    @Test
    public void testAdd() {
        Pile pile = new PileImpl();
        assertTrue(pile.add(createCard()));
    }
    
    @Test(expected = NullPointerException.class)
    public void testAddNPE() {
        Pile pile = new PileImpl();
        pile.add(null);
    }

    @Test(timeout = 5000)
    public void testShuffle() {
        boolean shuffled = false;
        Card card = createCard();
        Card card2 = createCard2();
        assertNotSame("card should be unequal to card2", card, card2);
        Pile pile = new PileImpl();
        pile.add(card);
        pile.add(card2);
        assertEquals("expected card2 on top", card2, pile.get(0));
        while (!shuffled) {
            pile.shuffle();
            if (!pile.get(0).equals(card2)) {
                shuffled = true;    //card2 is not on top anymore
            }
        }
        assertEquals("now expecting card on top", card, pile.get(0));
    }
    
    @Test
    public void testGet() {
        Pile pile = new PileImpl();
        Card card = createCard();
        pile.add(card);
        assertEquals("card should be equals to obtained element", card, pile.get(0));
    }
    
    @Test
    public void testGet2() {
        Pile pile = new PileImpl();
        Card card = createCard();
        Card card2 = createCard2();
        pile.add(card);
        pile.add(card2);
        assertEquals("card should now be second card", card, pile.get(1));
        assertEquals("card2 should now be first card", card2, pile.get(0));
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIOOBE() {
        Pile pile = new PileImpl();
        pile.get(0);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIOOBE2() {
        Pile pile = new PileImpl();
        pile.get(-1);
    }

    @Test
    public void testTake_integer() {
        Pile pile = new PileImpl();
        Card card = createCard();
        Card card2 = createCard2();
        assertNotSame("card should be unequal to card2", card, card2);
        pile.add(card);
        pile.add(card2);
        assertEquals("second card should be card", card, pile.take(1));
        assertEquals("first card should be card2", card2, pile.take(0));
        assertTrue(pile.isEmpty());
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testTake_integerIOOBE() {
        Pile pile = new PileImpl();
        pile.take(0);
    }
    
    @Test
    public void testIterator() {
        Pile pile = new PileImpl();
        Card card = createCard();
        Card card2 = createCard2();
        assertNotSame("card should be unequal to card2", card, card2);
        pile.add(card);
        pile.add(card2);
        Iterator<Card> iterator = pile.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("card on top should be card2", card2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("card below should be card", card, iterator.next());
        assertFalse(iterator.hasNext());
    }
    
    @Test
    public void testSpliterator() {
        assertNotNull(createFilledPile().spliterator());
    }
    
    @Test
    public void testGetEqualsTake() {
        Pile pile = new PileImpl();
        Card card = createCard();
        Card card2 = createCard2();
        assertNotSame(card, card2);
        pile.add(card);
        pile.add(card2);
        Card getFirst = pile.get(0);
        Card takeFirst = pile.take(0);
        assertEquals("first cards", getFirst, takeFirst);
        Card getSecond = pile.get(0);
        Card takeSecond = pile.take(0);
        assertEquals("second cards", getSecond, takeSecond);
        assertTrue(pile.isEmpty());
    }
    
    @Test
    public void testSize() {
        Pile pile = new PileImpl();
        assertEquals("empty pile", 0, pile.size());
        pile.add(createCard());
        assertEquals("pile with one card", 1, pile.size());
        pile.add(createCard2());
        assertEquals("pile with two cards", 2, pile.size());
    }
    
    @Test
    public void testForEach() {
        createFilledPile().forEach(Assert::assertNotNull);
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
    
    private Pile createFilledPile() {
        Pile pile = new PileImpl();
        Card card = createCard();
        Card card2 = createCard2();
        assertNotSame("card should be unequal to card2", card, card2);
        pile.add(card);
        pile.add(card2);
        return pile;
    }
    
    private static class PileImpl extends Pile {
        public PileImpl() {
            super();
        }
        
        public PileImpl(final Collection<? extends Card> cards) {
            super(cards);
        }
    }
}
