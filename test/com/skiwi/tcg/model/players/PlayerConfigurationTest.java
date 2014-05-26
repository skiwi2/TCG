
package com.skiwi.tcg.model.players;

import com.skiwi.tcg.model.cards.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class PlayerConfigurationTest {
    static {
        assertTrue(true);
    }
    
    @Test
    public void testConstructor() {
        TurnAction turnAction = new TurnActionImpl();
        List<Card> cards = new ArrayList<>();
        PlayerConfiguration playerConfiguration = new PlayerConfiguration(turnAction, 5, 7, cards);
        assertEquals(turnAction, playerConfiguration.getTurnAction());
        assertEquals(5, playerConfiguration.getHandCapacity());
        assertEquals(7, playerConfiguration.getFieldMonsterCapacity());
        assertEquals(cards, playerConfiguration.getDeckCards());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullTurnAction() {
        new PlayerConfiguration(null, 5, 5, Collections.emptyList());
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorNullDeckCards() {
        new PlayerConfiguration(new TurnActionImpl(), 5, 5, null);
    }
    
    @Test
    public void testGetTurnAction() {
        TurnAction turnAction = new TurnActionImpl();
        List<Card> cards = new ArrayList<>();
        PlayerConfiguration playerConfiguration = new PlayerConfiguration(turnAction, 5, 7, cards);
        assertEquals(turnAction, playerConfiguration.getTurnAction());
    }
    
    @Test
    public void testGetHandCapacity() {
        TurnAction turnAction = new TurnActionImpl();
        List<Card> cards = new ArrayList<>();
        PlayerConfiguration playerConfiguration = new PlayerConfiguration(turnAction, 5, 7, cards);
        assertEquals(5, playerConfiguration.getHandCapacity());
    }
    
    @Test
    public void testGetFieldMonsterCapacity() {
        TurnAction turnAction = new TurnActionImpl();
        List<Card> cards = new ArrayList<>();
        PlayerConfiguration playerConfiguration = new PlayerConfiguration(turnAction, 5, 7, cards);
        assertEquals(7, playerConfiguration.getFieldMonsterCapacity());
    }
    
    @Test
    public void testGetDeckCards() {
        TurnAction turnAction = new TurnActionImpl();
        List<Card> cards = new ArrayList<>();
        PlayerConfiguration playerConfiguration = new PlayerConfiguration(turnAction, 5, 7, cards);
        assertEquals(cards, playerConfiguration.getDeckCards());
    }
    
    private static class TurnActionImpl implements TurnAction {
        @Override
        public void performTurn(Player player) { }
    }
}