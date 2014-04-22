
package com.skiwi.tcg.utils;

import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public final class Arguments {
    private Arguments() {
        throw new UnsupportedOperationException();
    }

    public static int requirePositive(final int value) throws IllegalArgumentException {
        return requirePositive(value, "value");
    }

    public static int requirePositive(final int value, final String name) throws IllegalArgumentException {
        Objects.requireNonNull(name);
        if (value <= 0) {
            throw new IllegalArgumentException("the " + name + " must be positive: " + value);
        }
        return value;
    }

    public static int requireNegative(final int value) throws IllegalArgumentException {
        return requireNegative(value, "value");
    }

    public static int requireNegative(final int value, final String name) throws IllegalArgumentException {
        Objects.requireNonNull(name);
        if (value >= 0) {
            throw new IllegalArgumentException("the " + name + " must be negative: " + value);
        }
        return value;
    }

    public static int requirePositiveOrZero(final int value) throws IllegalArgumentException {
        return requirePositiveOrZero(value, "value");
    }

    public static int requirePositiveOrZero(final int value, final String name) throws IllegalArgumentException {
        Objects.requireNonNull(name);
        if (value < 0) {
            throw new IllegalArgumentException("the " + name + " must be positive or zero: " + value);
        }
        return value;
    }

    public static int requireNegativeOrZero(final int value) throws IllegalArgumentException {
        return requireNegativeOrZero(value, "value");
    }

    public static int requireNegativeOrZero(final int value, final String name) throws IllegalArgumentException {
        Objects.requireNonNull(name);
        if (value > 0) {
            throw new IllegalArgumentException("the " + name + " must be negative or zero: " + value);
        }
        return value;
    }

    public static int requireInRange(final int value, final int lowInclusive, final int highExclusive) throws IllegalArgumentException {
        return requireInRange(value, lowInclusive, highExclusive, "value");
    }

    public static int requireInRange(final int value, final int lowInclusive, final int highExclusive, final String name) throws IllegalArgumentException {
        Objects.requireNonNull(name);
        if (lowInclusive >= highExclusive) {
            throw new IllegalArgumentException("the lower inclusive bound is greater or equal to the higher exclusive bound: " + lowInclusive + " >= " + highExclusive);
        }
        if (value < lowInclusive || value >= highExclusive) {
            throw new IllegalArgumentException("the " + name + " was not in range: " + value + ", expected: [" + lowInclusive + ", " + highExclusive + ")");
        }
        return value;
    }

    public static int requireInRangeClosed(final int value, final int lowInclusive, final int highInclusive) throws IllegalArgumentException {
        return requireInRangeClosed(value, lowInclusive, highInclusive, "value");
    }

    public static int requireInRangeClosed(final int value, final int lowInclusive, final int highInclusive, final String name) throws IllegalArgumentException {
        Objects.requireNonNull(name);
        if (lowInclusive > highInclusive) {
            throw new IllegalArgumentException("the lower inclusive bound is greater or equal to the higher inclusive bound: " + lowInclusive + " >= " + highInclusive);
        }
        if (value < lowInclusive || value > highInclusive) {
            throw new IllegalArgumentException("the " + name + " was not in range: " + value + ", expected: [" + lowInclusive + ", " + highInclusive + ")]");
        }
        return value;
    }

    public static int requireIndexInRange(final int index, final int lowInclusive, final int highExclusive) throws IndexOutOfBoundsException {
        if (index < lowInclusive || index >= highExclusive) {
            throw new IndexOutOfBoundsException("the index was not in range: " + index + ", expected: [" + lowInclusive + ", " + highExclusive + ")");
        }
        return index;
    }

    public static int requireIndexInRangeClosed(final int index, final int lowInclusive, final int highInclusive) throws IndexOutOfBoundsException {
        if (index < lowInclusive || index > highInclusive) {
            throw new IndexOutOfBoundsException("the index was not in range: " + index + ", expected: [" + lowInclusive + ", " + highInclusive + "]");
        }
        return index;
    }
}
