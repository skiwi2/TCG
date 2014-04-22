
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.utils.Arguments;
import com.skiwi.tcg.utils.States;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 *
 * @author Frank van Heeswijk
 */
public class Hand extends AbstractCollection<Card> implements Collection<Card> {
    private final List<Card> list = new ArrayList<>();
    private final int capacity;

    public Hand(final int capacity) {
        this.capacity = Arguments.requirePositive(capacity, "capacity");
    }

    public boolean isFull() {
        return (list.size() == capacity);
    }
    
    @Override
    public boolean add(final Card card) {
        Objects.requireNonNull(card);
        States.requireFalse(isFull(), "hand is full");
        list.add(card);
        return true;
    }

    public Card get(final int index) {
        checkIndex(index);
        return list.get(index);
    }

    public Card play(final int index) {
        checkIndex(index);
        return list.remove(index);
    }

    public void swap(final int indexOne, final int indexTwo) {
        checkIndex(indexOne);
        checkIndex(indexTwo);
        Collections.swap(list, indexOne, indexTwo);
    }
    
    @Override
    public String toString() {
        return "Hand(" + capacity + ", " + list + ")";
    }

    @Override
    public Iterator<Card> iterator() {
        return list.iterator();
    }
    
    @Override
    public Spliterator<Card> spliterator() {
        return list.spliterator();
    }
    
    @Override
    public int size() {
        return list.size();
    }
    
    @Override
    public void forEach(final Consumer<? super Card> action) {
        Objects.requireNonNull(action);
        list.forEach(action);
    }
    
    private void checkIndex(final int index) {
        Arguments.requireIndexInRange(index, 0, size());
    }
}