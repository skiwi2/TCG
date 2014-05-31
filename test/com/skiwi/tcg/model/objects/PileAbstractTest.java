
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.cards.MonsterCard;
import java.util.Iterator;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
@Ignore
public abstract class PileAbstractTest {
    static {
        assertTrue(true);
    }
    
    private Pile pile;
    
    protected abstract Pile supplyPile();
    
    @Before
    final public void beforePileAbstractTest() {
        pile = supplyPile();
    }
    
    @Test
    public void testAdd() {
        assertTrue(pile.add(createCard()));
    }
    
    @Test(expected = NullPointerException.class)
    public void testAddNullCard() {
        pile.add(null);
    }

    @Test(timeout = 5000)
    public void testShuffle() {
        boolean shuffled = false;
        Card card = createCard();
        Card card2 = createCard2();
        assertNotSame("card should be unequal to card2", card, card2);
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
        Card card = createCard();
        pile.add(card);
        assertEquals("card should be equal to obtained element", card, pile.get(0));
    }
    
    @Test
    public void testGetTwoCards() {
        Card card = createCard();
        Card card2 = createCard2();
        pile.add(card);
        pile.add(card2);
        assertEquals("card should now be second card", card, pile.get(1));
        assertEquals("card2 should now be first card", card2, pile.get(0));
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTooHighIndex() {
        pile.get(0);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTooLowIndex() {
        pile.get(-1);
    }

    @Test
    public void testTake() {
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
    public void testTakeTooHighIndex() {
        pile.take(0);
    }
    
    @Test
    public void testIterator() {
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
    
    private Pile createFilledPile() {
        Card card = createCard();
        Card card2 = createCard2();
        assertNotSame("card should be unequal to card2", card, card2);
        pile.add(card);
        pile.add(card2);
        return pile;
    }
}
