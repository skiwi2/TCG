
package com.skiwi.tcg.utils;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * @author Frank van Heeswijk
 */
public final class Checker {
    private Checker() {
        throw new UnsupportedOperationException();
    }

    public static <E extends RuntimeException> void throwOnFail(final boolean shouldBeTrue, final Supplier<E> exceptionSupplier) throws E {
        Objects.requireNonNull(exceptionSupplier);
        if (!shouldBeTrue) {
            throw exceptionSupplier.get();
        }
    }

    public static <E extends RuntimeException> void throwOnFail(final boolean shouldBeTrue, final Function<String, E> exceptionFunction, final String errorMessage) throws E {
        Objects.requireNonNull(exceptionFunction);
        Objects.requireNonNull(errorMessage);
        if (!shouldBeTrue) {
            throw exceptionFunction.apply(errorMessage);
        }
    }

    public static <E extends RuntimeException> void throwOnSuccess(final boolean shouldBeFalse, final Supplier<E> exceptionSupplier) throws E {
        Objects.requireNonNull(exceptionSupplier);
        if (shouldBeFalse) {
            throw exceptionSupplier.get();
        }
    }

    public static <E extends RuntimeException> void throwOnSuccess(final boolean shouldBeFalse, final Function<String, E> exceptionFunction, final String errorMessage) throws E {
        Objects.requireNonNull(exceptionFunction);
        Objects.requireNonNull(errorMessage);
        if (shouldBeFalse) {
            throw exceptionFunction.apply(errorMessage);
        }
    }
    
    public static void checkArgument(final boolean shouldBeTrue) throws IllegalArgumentException {
        throwOnFail(shouldBeTrue, IllegalArgumentException::new);
    }
    
    public static void checkArgument(final boolean shouldBeTrue, final String errorMessage) throws IllegalArgumentException {
        Objects.requireNonNull(errorMessage);
        throwOnFail(shouldBeTrue, IllegalArgumentException::new, errorMessage);
    }
    
    public static void checkState(final boolean shouldBeTrue) throws IllegalStateException {
        throwOnFail(shouldBeTrue, IllegalStateException::new);
    }
    
    public static void checkState(final boolean shouldBeTrue, final String errorMessage) throws IllegalStateException {
        Objects.requireNonNull(errorMessage);
        throwOnFail(shouldBeTrue, IllegalStateException::new, errorMessage);
    }
    
    public static void checkIndex(final boolean shouldBeTrue) throws IndexOutOfBoundsException {
        throwOnFail(shouldBeTrue, IndexOutOfBoundsException::new);
    }
    
    public static void checkIndex(final boolean shouldBeTrue, final String errorMessage) throws IndexOutOfBoundsException {
        Objects.requireNonNull(errorMessage);
        throwOnFail(shouldBeTrue, IndexOutOfBoundsException::new, errorMessage);
    }
    
    public static void checkElement(final boolean shouldBeTrue) throws IndexOutOfBoundsException {
        throwOnFail(shouldBeTrue, NoSuchElementException::new);
    }
    
    public static void checkElement(final boolean shouldBeTrue, final String errorMessage) throws IndexOutOfBoundsException {
        Objects.requireNonNull(errorMessage);
        throwOnFail(shouldBeTrue, NoSuchElementException::new, errorMessage);
    }
}
