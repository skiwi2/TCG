
package com.skiwi.eventbus;

/**
 *
 * @author Frank van Heeswijk
 */
public final class EventBusFactory {
    private final static EventBus VOID_EVENT_BUS = new SimpleEventBus(NoEvent.class);
    
    private EventBusFactory() {
        throw new UnsupportedOperationException();
    }
    
    public static EventBus getVoidEventBus() {
        return VOID_EVENT_BUS;
    }
    
    private final static class NoEvent {
        private NoEvent() {
            throw new UnsupportedOperationException();
        }
    }
}
