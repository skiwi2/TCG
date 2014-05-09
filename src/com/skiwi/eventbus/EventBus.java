
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

    void removeListenersOfObject(final Object callbackObject);

    <T> void removeListener(final Class<T> eventClass, final Consumer<? super T> eventListener);

    void removeAllListenersOfEvent(final Class<?> eventClass);

    void removeAllListeners();
}
