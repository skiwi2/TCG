
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
public class GraveyardTest extends PileTest {
    static {
        assertTrue(true);
    }
    
    @Test
    public void testConstructor() {
        new Graveyard();
    }
    
    @Test
    public void testConstructor_collection() {
        new Graveyard(createCollection());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructor_collectionNPE() {
        new Graveyard(null);
    }
    
    @Test
    public void testToString() {
        Graveyard graveyard = new Graveyard();
        graveyard.addAll(createCollection());
        assertEquals("Graveyard(2)", graveyard.toString());
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