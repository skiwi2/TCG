
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.utils.ExceptionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public class Hand {
    private final List<Card> list = new ArrayList<>();
    private final int capacity;

    public Hand(final int capacity) {
        ExceptionUtils.throwOnFail(capacity > 0, IllegalArgumentException::new, "capacity should be strictly positive");
        this.capacity = capacity;
    }

    public boolean isFull() {
        return (list.size() == capacity);
    }

    public void add(final Card card) {
        Objects.requireNonNull(card);
        ExceptionUtils.throwOnSuccess(this::isFull, IllegalArgumentException::new, "hand is full");
        list.add(card);
    }
    
    public Card play(final int index) {
        assertIndex(index);
        return list.remove(index);
    }
    
    public void swap(final int indexOne, final int indexTwo) {
        assertIndex(indexOne);
        assertIndex(indexTwo);
        Collections.swap(list, indexTwo, indexTwo);
    }
    
    private void assertIndex(final int index) {
        ExceptionUtils.throwOnFail(index >= 0 && index < list.size(), IndexOutOfBoundsException::new);
    }
}