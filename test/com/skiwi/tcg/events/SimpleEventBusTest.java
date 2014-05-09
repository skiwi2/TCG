
package com.skiwi.tcg.events;

import com.skiwi.eventbus.SimpleEventBus;
import com.skiwi.eventbus.Event;
import com.skiwi.eventbus.EventBus;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Stream;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class SimpleEventBusTest {
    static {
        assertTrue(true);
    }

    private AtomicInteger alphaCounter;
    private AtomicInteger betaCounter;
    private AtomicInteger gammaCounter;

    @Before
    public void before() {
        alphaCounter = new AtomicInteger(0);
        betaCounter = new AtomicInteger(0);
        gammaCounter = new AtomicInteger(0);
    }

    private Stream<AtomicInteger> counters() {
        return Stream.of(alphaCounter, betaCounter, gammaCounter);
    }

    @Test
    public void testConstructor() {
        EventBus eventBus = new SimpleEventBus();
        eventBus.registerListenersOfObject(new Object() {
            @Event
            public void onAlphaEvent(final AlphaEvent alphaEvent) {
                alphaCounter.incrementAndGet();
            }
        });
        eventBus.executeEvent(new AlphaEvent());
        assertEquals(1, alphaCounter.get());
    }

    @Test
    public void testConstructorWithEventClassConstraint() {
        EventBus eventBus = new SimpleEventBus(BetaEvent.class);
        eventBus.registerListenersOfObject(new Object() {
            @Event
            public void onAlphaEvent(final AlphaEvent alphaEvent) {
                alphaCounter.incrementAndGet();
            }
        });
        eventBus.registerListener(AlphaEvent.class, alphaEvent -> alphaCounter.incrementAndGet());
        eventBus.executeEvent(new AlphaEvent());
        assertEquals(0, alphaCounter.get());
    }

    @Test
    public void testRegisterListenersOfObject() {
        EventBus eventBus = new SimpleEventBus();
        eventBus.registerListenersOfObject(new Object() {
            @Event
            public void onAlphaEvent1(final AlphaEvent alphaEvent) {
                alphaCounter.incrementAndGet();
            }

            @Event
            public void onAlphaEvent2(final AlphaEvent alphaEvent) {
                alphaCounter.incrementAndGet();
            }

            @Event
            public void onAlphaEvent3(final AlphaEvent alphaEvent) {
                alphaCounter.incrementAndGet();
            }

            @Event
            public void onBetaEvent1(final BetaEvent betaEvent) {
                betaCounter.incrementAndGet();
            }

            @Event
            public void onBetaEvent2(final BetaEvent betaEvent) {
                betaCounter.incrementAndGet();
            }

            @Event
            public void onGammaEvent(final GammaEvent gammaEvent) {
                gammaCounter.incrementAndGet();
            }
        });

        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        assertEquals(3, alphaCounter.get());
        assertEquals(2, betaCounter.get());
        assertEquals(1, gammaCounter.get());

        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        assertEquals(6, alphaCounter.get());
        assertEquals(4, betaCounter.get());
        assertEquals(2, gammaCounter.get());
    }

    @Test
    public void testRegisterListener() {
        EventBus eventBus = new SimpleEventBus();
        eventBus.registerListener(AlphaEvent.class, alphaEvent -> alphaCounter.incrementAndGet());
        eventBus.registerListener(AlphaEvent.class, alphaEvent -> alphaCounter.incrementAndGet());
        eventBus.registerListener(AlphaEvent.class, alphaEvent -> alphaCounter.incrementAndGet());
        eventBus.registerListener(BetaEvent.class, betaEvent -> betaCounter.incrementAndGet());
        eventBus.registerListener(BetaEvent.class, betaEvent -> betaCounter.incrementAndGet());
        eventBus.registerListener(GammaEvent.class, gammaEvent -> gammaCounter.incrementAndGet());

        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        assertEquals(3, alphaCounter.get());
        assertEquals(2, betaCounter.get());
        assertEquals(1, gammaCounter.get());

        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        assertEquals(6, alphaCounter.get());
        assertEquals(4, betaCounter.get());
        assertEquals(2, gammaCounter.get());
    }

    @Test
    public void testExecuteEvent() {
        EventBus eventBus = new SimpleEventBus();
        eventBus.registerListener(AlphaEvent.class, alphaEvent -> alphaCounter.incrementAndGet());
        eventBus.executeEvent(new AlphaEvent());
        assertEquals(1, alphaCounter.get());
    }

    @Test
    public void testExecuteEventSameInstance() {
        AlphaEvent specificAlphaEvent = new AlphaEvent();
        EventBus eventBus = new SimpleEventBus();
        eventBus.registerListener(AlphaEvent.class, alphaEvent -> assertTrue(alphaEvent == specificAlphaEvent));
    }

    @Test
    public void testRemoveListenersOfObject() {
        EventBus eventBus = new SimpleEventBus();
        Object object1 = new Object() {
            @Event
            public void onAlphaEvent(final AlphaEvent alphaEvent) {
                alphaCounter.incrementAndGet();
            }

            @Event
            public void onBetaEvent(final BetaEvent betaEvent) {
                betaCounter.incrementAndGet();
            }

            @Event
            public void onGammaEvent(final GammaEvent gammaEvent) {
                gammaCounter.incrementAndGet();
            }
        };
        Object object2 = new Object() {
            @Event
            public void onAlphaEvent(final AlphaEvent alphaEvent) {
                alphaCounter.incrementAndGet();
            }

            @Event
            public void onBetaEvent(final BetaEvent betaEvent) {
                betaCounter.incrementAndGet();
            }

            @Event
            public void onGammaEvent(final GammaEvent gammaEvent) {
                gammaCounter.incrementAndGet();
            }
        };

        eventBus.registerListenersOfObject(object1);
        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        counters().allMatch(counter -> counter.get() == 1);

        eventBus.registerListenersOfObject(object2);
        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        counters().allMatch(counter -> counter.get() == 3);

        eventBus.removeListenersOfObject(object2);
        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        counters().allMatch(counter -> counter.get() == 4);

        eventBus.removeListenersOfObject(object1);
        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        counters().allMatch(counter -> counter.get() == 4);
    }

    @Test
    public void testRemoveListener() {
        EventBus eventBus = new SimpleEventBus();
        Consumer<AlphaEvent> alphaEventListener = alphaEvent -> alphaCounter.incrementAndGet();
        Consumer<BetaEvent> betaEventListener = betaEvent -> betaCounter.incrementAndGet();
        Consumer<GammaEvent> gammaEventListener = gammaEvent -> gammaCounter.incrementAndGet();
        eventBus.registerListener(AlphaEvent.class, alphaEventListener);
        eventBus.registerListener(BetaEvent.class, betaEventListener);
        eventBus.registerListener(GammaEvent.class, gammaEventListener);

        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        assertEquals(1, alphaCounter.get());
        assertEquals(1, betaCounter.get());
        assertEquals(1, gammaCounter.get());

        eventBus.removeListener(GammaEvent.class, gammaEventListener);
        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        assertEquals(2, alphaCounter.get());
        assertEquals(2, betaCounter.get());
        assertEquals(1, gammaCounter.get()); 

        eventBus.removeListener(BetaEvent.class, betaEventListener);
        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        assertEquals(3, alphaCounter.get());
        assertEquals(2, betaCounter.get());
        assertEquals(1, gammaCounter.get()); 

        eventBus.removeListener(AlphaEvent.class, alphaEventListener);
        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        assertEquals(3, alphaCounter.get());
        assertEquals(2, betaCounter.get());
        assertEquals(1, gammaCounter.get()); 
    }

    @Test
    public void testRemoveAllListenersOfEvent() {
        EventBus eventBus = new SimpleEventBus();
        eventBus.registerListener(AlphaEvent.class, alphaEvent -> alphaCounter.incrementAndGet());
        eventBus.registerListener(AlphaEvent.class, alphaEvent -> alphaCounter.incrementAndGet());
        eventBus.registerListener(AlphaEvent.class, alphaEvent -> alphaCounter.incrementAndGet());
        eventBus.registerListener(BetaEvent.class, betaEvent -> betaCounter.incrementAndGet());
        eventBus.registerListener(BetaEvent.class, betaEvent -> betaCounter.incrementAndGet());
        eventBus.registerListener(GammaEvent.class, gammaEvent -> gammaCounter.incrementAndGet());

        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        assertEquals(3, alphaCounter.get());
        assertEquals(2, betaCounter.get());
        assertEquals(1, gammaCounter.get()); 

        eventBus.removeAllListenersOfEvent(AlphaEvent.class);
        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        assertEquals(3, alphaCounter.get());
        assertEquals(4, betaCounter.get());
        assertEquals(2, gammaCounter.get()); 

        eventBus.removeAllListenersOfEvent(BetaEvent.class);
        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        assertEquals(3, alphaCounter.get());
        assertEquals(4, betaCounter.get());
        assertEquals(3, gammaCounter.get()); 

        eventBus.removeAllListenersOfEvent(GammaEvent.class);
        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        assertEquals(3, alphaCounter.get());
        assertEquals(4, betaCounter.get());
        assertEquals(3, gammaCounter.get()); 
    }

    @Test
    public void testRemoveAllListeners() {
        EventBus eventBus = new SimpleEventBus();
        eventBus.registerListener(AlphaEvent.class, alphaEvent -> alphaCounter.incrementAndGet());
        eventBus.registerListener(AlphaEvent.class, alphaEvent -> alphaCounter.incrementAndGet());
        eventBus.registerListener(AlphaEvent.class, alphaEvent -> alphaCounter.incrementAndGet());
        eventBus.registerListener(BetaEvent.class, betaEvent -> betaCounter.incrementAndGet());
        eventBus.registerListener(BetaEvent.class, betaEvent -> betaCounter.incrementAndGet());
        eventBus.registerListener(GammaEvent.class, gammaEvent -> gammaCounter.incrementAndGet());

        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        assertEquals(3, alphaCounter.get());
        assertEquals(2, betaCounter.get());
        assertEquals(1, gammaCounter.get()); 

        eventBus.removeAllListeners();
        eventBus.executeEvent(new AlphaEvent());
        eventBus.executeEvent(new BetaEvent());
        eventBus.executeEvent(new GammaEvent());
        assertEquals(3, alphaCounter.get());
        assertEquals(2, betaCounter.get());
        assertEquals(1, gammaCounter.get()); 
    }
    
    @Test
    public void testRuntimeSubclassEvent() {
        Number expected = 1;
        AtomicReference<Number> actual = new AtomicReference<>(null);
        EventBus eventBus = new SimpleEventBus(Number.class);
        eventBus.registerListener(Number.class, num -> actual.set(num));
        eventBus.executeEvent(expected);
        assertEquals(expected, actual.get());
    }
    
    @Test
    public void testSuperEvents() {
        EventBus eventBus = new SimpleEventBus();
        eventBus.registerListener(Integer.class, alphaEvent -> alphaCounter.incrementAndGet());
        eventBus.registerListener(Number.class, alphaEvent -> alphaCounter.incrementAndGet());
        eventBus.registerListener(Object.class, alphaEvent -> alphaCounter.incrementAndGet());
        eventBus.executeEvent(1);
        assertEquals(3, alphaCounter.get());
    }

    private static class AlphaEvent { }

    private static class BetaEvent { }

    private static class GammaEvent { }
}