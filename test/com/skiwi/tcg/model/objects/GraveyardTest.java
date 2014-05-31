
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.cards.MonsterCard;
import java.util.ArrayList;
import java.util.Collection;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class GraveyardTest extends PileAbstractTest {
    static {
        assertTrue(true);
    }
    
    @Override
    protected Pile supplyPile() {
        return new Graveyard();
    }
    
    @Test
    public void testConstructor() {
        new Graveyard();
    }
    
    @Test
    public void testConstructorWithCollection() {
        Card card = createCard();
        Card card2 = createCard2();
        Collection<Card> list = new ArrayList<>();
        list.add(card);
        list.add(card2);
        assertNotSame(card, card2);
        Graveyard graveyard = new Graveyard(list);
        assertTrue(graveyard.contains(card));
        assertTrue(graveyard.contains(card2));
        assertEquals(2, graveyard.size());
    }
    
    @Test
    public void testConstructorWithRestrictedCollection() {
        MonsterCard card = createCard();
        MonsterCard card2 = createCard2();
        Collection<MonsterCard> list = new ArrayList<>();
        list.add(card);
        list.add(card2);
        assertNotSame(card, card2);
        Graveyard graveyard = new Graveyard(list);
        assertTrue(graveyard.contains(card));
        assertTrue(graveyard.contains(card2));
        assertEquals(2, graveyard.size());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullCollection() {
        new Graveyard(null);
    }
    
    @Test
    public void testTransferAll() {
        Card card = createCard();
        Card card2 = createCard2();
        assertNotSame(card, card2);
        Graveyard graveyard = new Graveyard();
        graveyard.add(card);
        graveyard.add(card2);
        assertEquals(2, graveyard.size());
        
        Collection<Card> collection = new ArrayList<>();
        graveyard.transferAll(collection);
        assertEquals(0, graveyard.size());
        assertEquals(2, collection.size());
        assertTrue(collection.contains(card));
        assertTrue(collection.contains(card2));
    }
    
    @Test
    public void testTransferAllWithSuperCollection() {
        Card card = createCard();
        Card card2 = createCard2();
        assertNotSame(card, card2);
        Graveyard graveyard = new Graveyard();
        graveyard.add(card);
        graveyard.add(card2);
        assertEquals(2, graveyard.size());
        
        Collection<Object> collection = new ArrayList<>();
        graveyard.transferAll(collection);
        assertEquals(0, graveyard.size());
        assertEquals(2, collection.size());
        assertTrue(collection.contains(card));
        assertTrue(collection.contains(card2));
    }
    
    @Test(expected = NullPointerException.class)
    public void testTransferAllNullCollection() {
        new Graveyard().transferAll(null);
    }
    
    @Test
    public void testToString() {
        Graveyard graveyard = new Graveyard();
        graveyard.addAll(createCollection());
        assertEquals(Graveyard.class.getSimpleName() + "(2)", graveyard.toString());
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
}