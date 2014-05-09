
package com.skiwi.eventbus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 *
 * @author Frank van Heeswijk
 */
public class SimpleEventBus implements EventBus {
    private final static Set<EventHandler> EMPTY_SET = Collections.emptySet();
    private final static EventHandler[] EMPTY_ARRAY = new EventHandler[0];
    private final static Supplier<Set<EventHandler>> CONCURRENT_SET_SUPPLIER =
            () -> Collections.newSetFromMap(new ConcurrentHashMap<>());

    private final ConcurrentMap<Class<?>, Set<EventHandler>> eventMapping = new ConcurrentHashMap<>();
    private final Class<?> eventClassConstraint;

    public SimpleEventBus() {
        this(Object.class);
    }

    public SimpleEventBus(final Class<?> eventClassConstraint) {
        this.eventClassConstraint = Objects.requireNonNull(eventClassConstraint);
    }

    @Override
    public void registerListenersOfObject(final Object callbackObject) {
        Arrays.stream(callbackObject.getClass().getMethods())
                .filter(this::isEligibleMethod)
                .forEach(method -> {
                    Class<?> eventClass = method.getParameterTypes()[0];
                    addEventHandler(eventClass, new MethodEventHandler(method, callbackObject, eventClass));
                });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void registerListener(final Class<T> eventClass, final Consumer<? super T> eventListener) {
        Objects.requireNonNull(eventClass);
        Objects.requireNonNull(eventListener);
        if (eventClassConstraint.isAssignableFrom(eventClass)) {
            addEventHandler(eventClass, new ConsumerEventHandler((Consumer<Object>)eventListener));
        }
    }

    @Override
    public void executeEvent(final Object event) {
        if (eventClassConstraint.isAssignableFrom(event.getClass())) {
            getCopyOfEventHandlers(event.getClass()).forEach(eventHandler -> eventHandler.invoke(event));
        }
    }

    @Override
    public void removeListenersOfObject(final Object callbackObject) {
        Arrays.stream(callbackObject.getClass().getMethods())
                .filter(this::isEligibleMethod)
                .forEach(method -> {
                    Class<?> eventClass = method.getParameterTypes()[0];
                    removeEventHandler(eventClass, new MethodEventHandler(method, callbackObject, eventClass));
                });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void removeListener(final Class<T> eventClass, final Consumer<? super T> eventListener) {
        Objects.requireNonNull(eventClass);
        Objects.requireNonNull(eventListener);
        if (eventClassConstraint.isAssignableFrom(eventClass)) {
            removeEventHandler(eventClass, new ConsumerEventHandler((Consumer<Object>)eventListener));
        }
    }

    @Override
    public void removeAllListenersOfEvent(final Class<?> eventClass) {
        eventMapping.remove(Objects.requireNonNull(eventClass));
    }

    @Override
    public void removeAllListeners() {
        eventMapping.clear();
    }

    private boolean isEligibleMethod(final Method method) {
        return (method.getAnnotation(Event.class) != null
                && method.getReturnType().equals(void.class)
                && method.getParameterCount() == 1
                && eventClassConstraint.isAssignableFrom(method.getParameterTypes()[0]));
    }

    private void addEventHandler(final Class<?> eventClass, final EventHandler eventHandler) {
        Objects.requireNonNull(eventClass);
        Objects.requireNonNull(eventHandler);
        eventMapping.computeIfAbsent(eventClass, key -> CONCURRENT_SET_SUPPLIER.get())
                .add(eventHandler);
    }

    private void removeEventHandler(final Class<?> eventClass, final EventHandler eventHandler) {
        Objects.requireNonNull(eventClass);
        Objects.requireNonNull(eventHandler);
        eventMapping.getOrDefault(eventClass, EMPTY_SET).remove(eventHandler);
    }

    private Stream<EventHandler> getCopyOfEventHandlers(final Class<?> eventClass) {
        Stream.Builder<Set<EventHandler>> builder = Stream.builder();
        for (Class<?> clazz = Objects.requireNonNull(eventClass); clazz != null; clazz = clazz.getSuperclass()) {
            builder.add(eventMapping.getOrDefault(clazz, EMPTY_SET));
        }
        return builder.build()
                .filter(handlers -> !handlers.isEmpty())
                .map(handlers -> handlers.toArray(EMPTY_ARRAY))
                .flatMap(copyArray -> Arrays.stream(copyArray));
    }

    private static interface EventHandler {
        void invoke(final Object event);
    }

    private static class MethodEventHandler implements EventHandler {
        private final Method method;
        private final Object callbackObject;
        private final Class<?> eventClass;

        public MethodEventHandler(final Method method, final Object object, final Class<?> eventClass) {
            this.method = Objects.requireNonNull(method);
            this.callbackObject = Objects.requireNonNull(object);
            this.eventClass = Objects.requireNonNull(eventClass);
        }

        @Override
        public void invoke(final Object event) {
            try {
                method.setAccessible(true);
                method.invoke(callbackObject, Objects.requireNonNull(event));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 71 * hash + Objects.hashCode(this.method);
            hash = 71 * hash + Objects.hashCode(this.callbackObject);
            hash = 71 * hash + Objects.hashCode(this.eventClass);
            return hash;
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final MethodEventHandler other = (MethodEventHandler)obj;
            if (!Objects.equals(this.method, other.method)) {
                return false;
            }
            if (!Objects.equals(this.callbackObject, other.callbackObject)) {
                return false;
            }
            if (!Objects.equals(this.eventClass, other.eventClass)) {
                return false;
            }
            return true;
        }
    }

    private static class ConsumerEventHandler implements EventHandler {
        private final Consumer<Object> eventListener;

        public ConsumerEventHandler(final Consumer<Object> consumer) {
            this.eventListener = Objects.requireNonNull(consumer);
        }

        @Override
        public void invoke(final Object event) {
            eventListener.accept(Objects.requireNonNull(event));
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 19 * hash + Objects.hashCode(this.eventListener);
            return hash;
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ConsumerEventHandler other = (ConsumerEventHandler)obj;
            if (!Objects.equals(this.eventListener, other.eventListener)) {
                return false;
            }
            return true;
        }
    }
}
