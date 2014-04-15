
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.cards.MonsterCard;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class HandTest {
    {
        assertEquals(true, true);
    }

    @Test
    public void testConstructor() {
        Hand hand = new Hand(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIAE() {
        Hand hand = new Hand(0);
    }

    @Test
    public void testIsFull() {
        Hand hand = new Hand(2);
        hand.add(createCard());
        assertEquals("hand should not be full", false, hand.isFull());
        hand.add(createCard());
        assertEquals("hand should be full", true, hand.isFull());
        hand.play(1);
        assertEquals("hand should not be full anymore", false, hand.isFull());
    }

    @Test
    public void testAdd() {
        Hand hand = new Hand(1);
        hand.add(createCard());
    }

    @Test(expected = NullPointerException.class)
    public void testAddNPE() {
        Hand hand = new Hand(1);
        hand.add(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testAddISE() {
        Hand hand = new Hand(1);
        hand.add(createCard());
        hand.add(createCard());
    }

    @Test
    public void testPlay() {
        Hand hand = new Hand(1);
        Card card = createCard();
        hand.add(card);
        assertEquals("card should be equal", card, hand.play(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testPlayIOOBE1() {
        Hand hand = new Hand(1);
        hand.play(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testPlayIOOBE2() {
        Hand hand = new Hand(1);
        hand.play(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testPlayIOOB3() {
        Hand hand = new Hand(1);
        hand.add(createCard());
        hand.play(1);
    }

    @Test
    public void testSwap() {
        Hand hand = new Hand(2);
        Card card = createCard();
        Card card2 = createCard2();
        hand.add(card);
        hand.add(card2);
        hand.swap(0, 1);
        assertEquals("card should be equal", card, hand.play(1));
        assertEquals("card2 should be equal", card2, hand.play(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSwapIOOBE1() {
        Hand hand = new Hand(1);
        hand.add(createCard());
        hand.swap(-1, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSwapIOOBE2() {
        Hand hand = new Hand(1);
        hand.add(createCard());
        hand.swap(1, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSwapIOOBE3() {
        Hand hand = new Hand(1);
        hand.add(createCard());
        hand.swap(0, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSwapIOOBE4() {
        Hand hand = new Hand(1);
        hand.add(createCard());
        hand.swap(0, 1);
    }

    @Test
    public void testToString1() {
        Hand hand = new Hand(1);
        assertEquals("Hand(1, [])", hand.toString());
    }

    @Test
    public void testToString2() {
        Hand hand = new Hand(2);
        Card card = createCard();
        Card card2 = createCard2();
        hand.add(card);
        hand.add(card2);
        assertEquals("Hand(2, [" + card + ", " + card2 + "])", hand.toString());
    }

    private Card createCard() {
        return new MonsterCard("Test", 10, 100, MonsterModus.OFFENSIVE);
    }

    private Card createCard2() {
        return new MonsterCard("Test2", 15, 150, MonsterModus.HEALING);
    }
}
