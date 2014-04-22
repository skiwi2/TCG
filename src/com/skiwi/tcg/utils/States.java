
package com.skiwi.tcg.utils;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public final class States {
    private States() {
        throw new UnsupportedOperationException();
    }
    
    public static boolean requireTrue(final boolean condition) throws IllegalStateException {
        return requireTrue(condition, "condition must be true");
    }
    
    public static boolean requireTrue(final boolean condition, final String message) throws IllegalStateException {
        Objects.requireNonNull(message);
        if (!condition) {
            throw new IllegalStateException(message);
        }
        return condition;
    }
    
    public static boolean requireFalse(final boolean condition) throws IllegalStateException {
        return requireFalse(condition, "condition must be false");
    }
    
    public static boolean requireFalse(final boolean condition, final String message) throws IllegalStateException {
        Objects.requireNonNull(message);
        if (condition) {
            throw new IllegalStateException(message);
        }
        return condition;
    }
    
    public static <E, C extends Collection<E>> C requireEmpty(final C collection) throws IllegalStateException {
        return requireEmpty(collection, "collection");
    }
    
    public static <E, C extends Collection<E>> C requireEmpty(final C collection, final String name) throws IllegalStateException {
        Objects.requireNonNull(name);
        if (!collection.isEmpty()) {
            throw new IllegalStateException(name + " must be empty");
        }
        return collection;
    }
    
    public static <E, C extends Collection<E>> C requireNonEmpty(final C collection) throws NoSuchElementException {
        return requireNonEmpty(collection, "collection");
    }
    
    public static <E, C extends Collection<E>> C requireNonEmpty(final C collection, final String name) throws NoSuchElementException {
        Objects.requireNonNull(name);
        if (collection.isEmpty()) {
            throw new NoSuchElementException(name + " must be non-empty");
        }
        return collection;
    }
}
