
package com.skiwi.eventbus;

import java.util.function.Consumer;

/**
 *
 * @author Frank van Heeswijk
 */
public interface EventBus {
    void registerListenersOfObject(final Object callbackObject);

    <T> void registerListener(final Class<T> eventClass, final Consumer<? super T> eventListener);

    void executeEvent(final Object event);

    void deregisterListenersOfObject(final Object callbackObject);

    <T> void deregisterListener(final Class<T> eventClass, final Consumer<? super T> eventListener);

    void deregisterAllListenersOfEvent(final Class<?> eventClass);

    void deregisterAllListeners();
}
