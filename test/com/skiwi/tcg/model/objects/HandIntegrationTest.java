
package com.skiwi.tcg.model.objects;

import com.skiwi.eventbus.EventBus;
import com.skiwi.eventbus.SimpleEventBus;
import com.skiwi.tcg.events.HandAddedEvent;
import com.skiwi.tcg.events.HandEvent;
import com.skiwi.tcg.events.HandPlayedEvent;
import com.skiwi.tcg.events.HandSwappedEvent;
import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.cards.MonsterCard;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class HandIntegrationTest {
    private EventBus eventBus;
    private AtomicInteger counter;
    
    static {
        assertTrue(true);
    }
    
    @Before
    public void before() {
        this.eventBus = new SimpleEventBus();
        this.counter = new AtomicInteger(0);
    }
    
    @Test
    public void testAdd() {
        Hand hand = new Hand(5);
        hand.setEventBus(eventBus);
        final AtomicReference<Hand> handReference = new AtomicReference<>();
        final AtomicReference<Card> cardReference = new AtomicReference<>();
        
        eventBus.registerListener(HandAddedEvent.class, handAddedEvent -> {
            counter.incrementAndGet();
            handReference.set(handAddedEvent.getHand());
            cardReference.set(handAddedEvent.getCard());
        });
        Card card = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        hand.add(card);
        
        assertEquals(1, counter.get());
        assertEquals(hand, handReference.get());
        assertEquals(card, cardReference.get());
        
        counter.set(0);
        handReference.set(null);
        cardReference.set(null);
        
        eventBus.deregisterAllListenersOfEvent(HandAddedEvent.class);
        hand.add(card);
        
        assertEquals(0, counter.get());
        assertEquals(null, handReference.get());
        assertEquals(null, cardReference.get());
    };
    
    @Test
    public void testPlay() {
        Hand hand = new Hand(5);
        hand.setEventBus(eventBus);
        final AtomicReference<Hand> handReference = new AtomicReference<>();
        final AtomicInteger indexReference = new AtomicInteger(-1);
        final AtomicReference<Card> cardReference = new AtomicReference<>();
        
        eventBus.registerListener(HandPlayedEvent.class, handPlayedEvent -> {
            counter.incrementAndGet();
            handReference.set(handPlayedEvent.getHand());
            indexReference.set(handPlayedEvent.getIndex());
            cardReference.set(handPlayedEvent.getCard());
        });
        Card card = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        hand.add(card);
        hand.play(0);
        
        assertEquals(1, counter.get());
        assertEquals(hand, handReference.get());
        assertEquals(0, indexReference.get());
        assertEquals(card, cardReference.get());
        
        counter.set(0);
        handReference.set(null);
        indexReference.set(-1);
        cardReference.set(null);
        
        eventBus.deregisterAllListenersOfEvent(HandPlayedEvent.class);
        hand.add(card);
        hand.play(0);
        
        assertEquals(0, counter.get());
        assertEquals(null, handReference.get());
        assertEquals(-1, indexReference.get());
        assertEquals(null, cardReference.get());
    }
    
    @Test
    public void testSwap() {
        Hand hand = new Hand(5);
        hand.setEventBus(eventBus);
        final AtomicReference<Hand> handReference = new AtomicReference<>();
        final AtomicInteger indexOneReference = new AtomicInteger(-1);
        final AtomicInteger indexTwoReference = new AtomicInteger(-1);
        
        eventBus.registerListener(HandSwappedEvent.class, handSwappedEvent -> {
            counter.incrementAndGet();
            handReference.set(handSwappedEvent.getHand());
            indexOneReference.set(handSwappedEvent.getIndexOne());
            indexTwoReference.set(handSwappedEvent.getIndexTwo());
        });
        Card cardOne = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        Card cardTwo = new MonsterCard("Test 2", 7, 7, MonsterModus.OFFENSIVE);
        hand.add(cardOne);
        hand.add(cardTwo);
        hand.swap(0, 1);
        
        assertEquals(1, counter.get());
        assertEquals(hand, handReference.get());
        assertEquals(0, indexOneReference.get());
        assertEquals(1, indexTwoReference.get());
        
        counter.set(0);
        handReference.set(null);
        indexOneReference.set(-1);
        indexTwoReference.set(-1);
        
        eventBus.deregisterAllListenersOfEvent(HandSwappedEvent.class);
        hand.swap(0, 1);
        
        assertEquals(0, counter.get());
        assertEquals(null, handReference.get());
        assertEquals(-1, indexOneReference.get());
        assertEquals(-1, indexTwoReference.get());
    }
    
    @Test
    public void testHandEvent() {
        Hand hand = new Hand(5);
        hand.setEventBus(eventBus);
        
        eventBus.registerListener(HandEvent.class, e -> counter.incrementAndGet());
        Card cardOne = new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
        Card cardTwo = new MonsterCard("Test 2", 7, 7, MonsterModus.OFFENSIVE);
        hand.add(cardOne);
        hand.add(cardTwo);
        hand.swap(0, 1);
        assertEquals(cardTwo, hand.play(0));
        assertEquals(cardOne, hand.play(0));
        
        assertEquals(5, counter.get());
        
        counter.set(0);
        
        eventBus.deregisterAllListenersOfEvent(HandEvent.class);
        hand.add(cardOne);
        hand.add(cardTwo);
        hand.swap(0, 1);
        assertEquals(cardTwo, hand.play(0));
        assertEquals(cardOne, hand.play(0));
        
        assertEquals(0, counter.get());
    }
}