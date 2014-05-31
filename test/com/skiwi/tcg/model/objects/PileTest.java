
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.cards.MonsterCard;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class PileTest {
    static {
        assertTrue(true);
    }
    
    @Test
    public void testConstructor() {
        new PileImpl();
    }
    
    @Test
    public void testConstructorWithCollection() {
        new PileImpl(createCollection());
    }
    
    @Test
    public void testConstructorWithRestrictedCollection() {
        new PileImpl(createRestrictedCollection());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullCollection() {
        new PileImpl(null);
    }

    private MonsterCard createCard() {
        return new MonsterCard("Test", 10, 100, MonsterModus.OFFENSIVE);
    }

    private MonsterCard createCard2() {
        return new MonsterCard("Test2", 15, 150, MonsterModus.HEALING);
    }
    
    private Collection<Card> createCollection() {
        Collection<Card> collection = new ArrayList<>();
        collection.add(createCard());
        collection.add(createCard2());
        return collection;
    }
    
    private Collection<MonsterCard> createRestrictedCollection() {
        Collection<MonsterCard> collection = new ArrayList<>();
        collection.add(createCard());
        collection.add(createCard2());
        return collection;
    }
    
    private static class PileImpl extends Pile {
        private PileImpl() {
            super();
        }
        
        private PileImpl(final Collection<? extends Card> cards) {
            super(cards);
        }
    }
}
