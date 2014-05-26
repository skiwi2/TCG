
package com.skiwi.tcg.model.players;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.cards.MonsterCard;
import com.skiwi.tcg.model.objects.MonsterModus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class PlayerConfigurationBuilderTest {
    static {
        assertTrue(true);
    }
    
    private PlayerConfigurationBuilder builder;
    
    @Before
    public void before() {
        builder = new PlayerConfigurationBuilder();
    }
    
    @Test
    public void testTurnAction() {
        assertNotNull(builder.turnAction(new TurnActionImpl()));
    }
    
    @Test(expected = NullPointerException.class)
    public void testTurnActionNullTurnAction() {
        builder.turnAction(null);
    }
    
    @Test
    public void testHandCapacity() {
        assertNotNull(builder.handCapacity(5));
    }
    
    @Test
    public void testFieldMonsterCapacity() {
        assertNotNull(builder.fieldMonsterCapacity(5));
    }
    
    @Test
    public void testDeckCards() {
        assertNotNull(builder.deckCards(Collections.emptyList()));
    }
    
    @Test(expected = NullPointerException.class)
    public void testDeckCardsNullDeckCards() {
        builder.deckCards(null);
    }
    
    @Test
    public void testBuild() {
        TurnAction turnAction = new TurnActionImpl();
        List<Card> cards = new ArrayList<>();
        cards.add(new MonsterCard("Test", 5, 5, MonsterModus.HEALING));
        PlayerConfiguration playerConfiguration = new PlayerConfigurationBuilder()
                .turnAction(turnAction)
                .handCapacity(5)
                .fieldMonsterCapacity(7)
                .deckCards(cards)
                .build();
        assertEquals(turnAction, playerConfiguration.getTurnAction());
        assertEquals(5, playerConfiguration.getHandCapacity());
        assertEquals(7, playerConfiguration.getFieldMonsterCapacity());
        assertEquals(cards, playerConfiguration.getDeckCards());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testBuildTurnActionMissing() {
        new PlayerConfigurationBuilder()
                .handCapacity(5)
                .fieldMonsterCapacity(5)
                .deckCards(Collections.emptyList())
                .build();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testBuildHandCapacityMissing() {
        new PlayerConfigurationBuilder()
                .turnAction(new TurnActionImpl())
                .fieldMonsterCapacity(5)
                .deckCards(Collections.emptyList())
                .build();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testBuildFieldMonsterCapacityMissing() {
        new PlayerConfigurationBuilder()
                .turnAction(new TurnActionImpl())
                .handCapacity(5)
                .deckCards(Collections.emptyList())
                .build();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testBuildDeckCardsMissing() {
        new PlayerConfigurationBuilder()
                .turnAction(new TurnActionImpl())
                .handCapacity(5)
                .fieldMonsterCapacity(5)
                .build();
    }
    
    private static class TurnActionImpl implements TurnAction {
        @Override
        public void performTurn(final Player player) { }
    }
}