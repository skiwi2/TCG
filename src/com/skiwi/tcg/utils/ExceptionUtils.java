
package com.skiwi.tcg.utils;

import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * @author Frank van Heeswijk
 */
public final class ExceptionUtils {
    private ExceptionUtils() {
        throw new UnsupportedOperationException();
    }
    
    public static <E extends RuntimeException> void throwOnFail(final BooleanSupplier resultSupplier, final Supplier<E> exceptionSupplier) throws E {
        Objects.requireNonNull(resultSupplier);
        throwOnFail(resultSupplier.getAsBoolean(), exceptionSupplier);
    }
    
    public static <E extends RuntimeException> void throwOnFail(final boolean result, final Supplier<E> exceptionSupplier) throws E {
        Objects.requireNonNull(exceptionSupplier);
        if (!result) {
            throw exceptionSupplier.get();
        }
    }
    
    public static <E extends RuntimeException> void throwOnFail(final BooleanSupplier resultSupplier, final Function<String, E> exceptionFunction, final String message) throws E {
        Objects.requireNonNull(resultSupplier);
        throwOnFail(resultSupplier.getAsBoolean(), exceptionFunction, message);
    }
    
    public static <E extends RuntimeException> void throwOnFail(final boolean result, final Function<String, E> exceptionFunction, final String message) throws E {
        Objects.requireNonNull(exceptionFunction);
        Objects.requireNonNull(message);
        if (!result) {
            throw exceptionFunction.apply(message);
        }
    }
}
