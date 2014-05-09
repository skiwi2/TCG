
package com.skiwi.eventbus;

import java.util.function.Consumer;

/**
 *
 * @author Frank van Heeswijk
 */
public class VoidEventBus implements EventBus {
    @Override
    public void registerListenersOfObject(final Object callbackObject) { }

    @Override
    public <T> void registerListener(final Class<T> eventClass, final Consumer<? super T> eventListener) { }

    @Override
    public void executeEvent(final Object event) { }

    @Override
    public void removeListenersOfObject(final Object callbackObject) { }

    @Override
    public <T> void removeListener(final Class<T> eventClass, final Consumer<? super T> eventListener) { }

    @Override
    public void removeAllListenersOfEvent(final Class<?> eventClass) { }

    @Override
    public void removeAllListeners() {  }
}
