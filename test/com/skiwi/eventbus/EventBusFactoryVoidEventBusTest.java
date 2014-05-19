
package com.skiwi.eventbus;

import com.skiwi.tcg.events.HandEvent;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class EventBusFactoryVoidEventBusTest {
    private EventBus voidEventBus;
    
    static {
        assertTrue(true);
    }
    
    @Before
    public void before() {
        this.voidEventBus = EventBusFactory.getVoidEventBus();
    }

    @Test
    public void testRegisterListenersOfObject() {
        voidEventBus.registerListenersOfObject(new Object() {
            @Event
            public void onTestEvent(final TestEvent testEvent) { }
        });
    }
    
    @Test
    public void testRegisterListener() {
        voidEventBus.registerListener(TestEvent.class, testEvent -> { });
    }
    
    @Test
    public void testExecuteEvent() {
        voidEventBus.executeEvent(new TestEvent());
    }
    
    @Test
    public void testDeregisterListenersOfObject() {
        voidEventBus.deregisterListenersOfObject(new Object() {
            @Event
            public void onTestEvent(final TestEvent testEvent) { }
        });
    }
    
    @Test
    public void testDeregisterListener() {
        voidEventBus.deregisterListener(TestEvent.class, testEvent -> { });
    }
    
    @Test
    public void testDeregisterAllListenersOfEvent() {
        voidEventBus.deregisterAllListenersOfEvent(HandEvent.class);
    }
    
    @Test
    public void testDeregisterAllListeners() {
        voidEventBus.deregisterAllListeners();
    }
    
    @Test
    public void testAddListenerAndExecuteEvent() {
        final AtomicInteger counter = new AtomicInteger(0);
        voidEventBus.registerListener(TestEvent.class, testEvent -> counter.incrementAndGet());
        voidEventBus.executeEvent(new TestEvent());
        assertEquals(0, counter.get());
    }
    
    private static class TestEvent { }
}