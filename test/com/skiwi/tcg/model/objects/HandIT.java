
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.cards.MonsterCard;
import com.skiwi.tcg.view.objects.HandView;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class HandIT {
    static {
        assertTrue(true);
    }

    @Test
    public void testAdd() {
        Hand hand = new Hand(5);
        final AtomicInteger counter = new AtomicInteger(0);
        final AtomicReference<Card> cardReferenceOne = new AtomicReference<>();
        final AtomicReference<Card> cardReferenceTwo = new AtomicReference<>();

        hand.addViewCallback(new HandView() {
            @Override
            public void onCardAdded(final Card card) {
                counter.incrementAndGet();
                cardReferenceOne.set(card);
            }

            @Override
            public void onCardPlayed(final int cardIndex) { }

            @Override
            public void onCardsSwapped(final int cardIndexOne, final int cardIndexTwo) { }
        });
        hand.addViewCallback(new HandView() {
            @Override
            public void onCardAdded(final Card card) {
                counter.incrementAndGet();
                cardReferenceTwo.set(card);
            }

            @Override
            public void onCardPlayed(final int cardIndex) { }

            @Override
            public void onCardsSwapped(final int cardIndexOne, final int cardIndexTwo) { }
        });

        Card card = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        hand.add(card);

        assertEquals(2, counter.get());
        assertEquals(card, cardReferenceOne.get());
        assertEquals(card, cardReferenceTwo.get());
    }

    @Test
    public void testPlay() {
        Hand hand = new Hand(5);
        hand.add(new MonsterCard("Test", 5, 5, MonsterModus.HEALING));
        final AtomicInteger counter = new AtomicInteger(0);
        final AtomicInteger indexReferenceOne = new AtomicInteger(-1);
        final AtomicInteger indexReferenceTwo = new AtomicInteger(-1);

        hand.addViewCallback(new HandView() {
            @Override
            public void onCardAdded(final Card card) { }

            @Override
            public void onCardPlayed(final int cardIndex) {
                counter.incrementAndGet();
                indexReferenceOne.set(cardIndex);
            }

            @Override
            public void onCardsSwapped(final int cardIndexOne, final int cardIndexTwo) { }
        });
        hand.addViewCallback(new HandView() {
            @Override
            public void onCardAdded(final Card card) { }

            @Override
            public void onCardPlayed(final int cardIndex) {
                counter.incrementAndGet();
                indexReferenceTwo.set(cardIndex);
            }

            @Override
            public void onCardsSwapped(final int cardIndexOne, final int cardIndexTwo) { }
        });

        hand.play(0);

        assertEquals(2, counter.get());
        assertEquals(0, indexReferenceOne.get());
        assertEquals(0, indexReferenceTwo.get());
    }

    @Test
    public void testSwap() {
        Hand hand = new Hand(5);
        hand.add(new MonsterCard("Test", 5, 5, MonsterModus.HEALING));
        hand.add(new MonsterCard("Random", 3, 7, MonsterModus.OFFENSIVE));
        final AtomicInteger counter = new AtomicInteger(0);
        final AtomicInteger indexOneReferenceOne = new AtomicInteger(-1);
        final AtomicInteger indexTwoReferenceOne = new AtomicInteger(-1);
        final AtomicInteger indexOneReferenceTwo = new AtomicInteger(-1);
        final AtomicInteger indexTwoReferenceTwo = new AtomicInteger(-1);

        hand.addViewCallback(new HandView() {
            @Override
            public void onCardAdded(final Card card) { }

            @Override
            public void onCardPlayed(final int cardIndex) { }

            @Override
            public void onCardsSwapped(final int cardIndexOne, final int cardIndexTwo) {
                counter.incrementAndGet();
                indexOneReferenceOne.set(cardIndexOne);
                indexTwoReferenceOne.set(cardIndexTwo);
            }
        });
        hand.addViewCallback(new HandView() {
            @Override
            public void onCardAdded(final Card card) { }

            @Override
            public void onCardPlayed(final int cardIndex) { }

            @Override
            public void onCardsSwapped(final int cardIndexOne, final int cardIndexTwo) {
                counter.incrementAndGet();
                indexOneReferenceTwo.set(cardIndexOne);
                indexTwoReferenceTwo.set(cardIndexTwo);
            }
        });

        hand.swap(0, 1);

        assertEquals(2, counter.get());
        assertEquals(0, indexOneReferenceOne.get());
        assertEquals(1, indexTwoReferenceOne.get());
        assertEquals(0, indexOneReferenceTwo.get());
        assertEquals(1, indexTwoReferenceTwo.get());
    }
}