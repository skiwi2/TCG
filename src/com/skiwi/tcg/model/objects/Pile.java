
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.utils.Checker;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 *
 * @author Frank van Heeswijk
 */
public abstract class Pile extends AbstractCollection<Card> implements Collection<Card> {
    private final LinkedList<Card> linkedList = new LinkedList<>();
    
    public Pile() { }
    
    public Pile(final Collection<? extends Card> cards) {
        Objects.requireNonNull(cards);
        addAll(cards);
    }
    
    @Override
    public boolean add(final Card card) {
        Objects.requireNonNull(card);
        linkedList.addFirst(card);
        return true;
    }
    
    public void shuffle() {
        Collections.shuffle(linkedList);
    }
    
    public Card get(int index) {
        checkIndex(index);
        return linkedList.get(index);
    }
    
    public Card take(final int index) {
        checkIndex(index);
        return linkedList.remove(index);
    }
    
    @Override
    public Iterator<Card> iterator() {
        return linkedList.iterator();
    }
    
    @Override
    public Spliterator<Card> spliterator() {
        return linkedList.spliterator();
    }
    
    @Override
    public int size() {
        return linkedList.size();
    }
    
    @Override
    public void forEach(final Consumer<? super Card> action) {
        Objects.requireNonNull(action);
        linkedList.forEach(action);
    }
    
    private void checkIndex(final int index) {
        Checker.checkIndex(index >= 0 && index < linkedList.size(), "index should be between 0 and " + linkedList.size());
    }
}
