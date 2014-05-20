
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.cards.MonsterCard;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class GraveyardTest {
    static {
        assertTrue(true);
    }
    
    @Test
    public void testConstructor() {
        new Graveyard();
    }
    
    @Test
    public void testConstructor_collection() {
        Card card = createCard();
        Card card2 = createCard2();
        List<Card> list = new ArrayList<>();
        list.add(card);
        list.add(card2);
        assertNotSame(card, card2);
        Graveyard graveyard = new Graveyard(list);
        assertTrue(graveyard.contains(card));
        assertTrue(graveyard.contains(card2));
        assertEquals(2, graveyard.size());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructor_collectionNPE() {
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
        
        List<Card> list = new ArrayList<>();
        graveyard.transferAll(list);
        assertEquals(0, graveyard.size());
        assertEquals(2, list.size());
        assertTrue(list.contains(card));
        assertTrue(list.contains(card2));
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
    
    private Card createCard() {
        return new MonsterCard("Test", 10, 100, MonsterModus.OFFENSIVE);
    }

    private Card createCard2() {
        return new MonsterCard("Test2", 15, 150, MonsterModus.HEALING);
    }
    
    private Collection<Card> createCollection() {
        Collection<Card> collection = new ArrayList<>();
        collection.add(createCard());
        collection.add(createCard2());
        return collection;
    }
}