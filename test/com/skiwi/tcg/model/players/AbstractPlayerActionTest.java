
package com.skiwi.tcg.model.players;

import com.skiwi.tcg.model.cards.MonsterCard;
import com.skiwi.tcg.model.objects.Deck;
import com.skiwi.tcg.model.objects.Field;
import com.skiwi.tcg.model.objects.Graveyard;
import com.skiwi.tcg.model.objects.Hand;
import com.skiwi.tcg.model.objects.MonsterModus;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class AbstractPlayerActionTest {
    static {
        assertTrue(true);
    }
    
    private AtomicInteger counter;
    
    @Before
    public void before() {
        counter = new AtomicInteger(0);
    }
    
    @Test
    public void testPerformAction() {
        PlayerAction playerAction = new AbstractPlayerActionImpl();
        Player player = new Player("Test", 100, p -> { }, new Hand(5), new Field(5), new Deck(Arrays.asList(new MonsterCard("Test", 5, 5, MonsterModus.HEALING))), new Graveyard());
        playerAction.performAction(player);
        assertEquals(1, counter.get());
    }
    
    @Test(expected = NullPointerException.class)
    public void testPerformActionNullPlayer() {
        PlayerAction playerAction = new AbstractPlayerActionImpl();
        playerAction.performAction(null);
    }
    
    @Test(expected = PlayerActionNotAllowedException.class)
    public void testPerformActionNotAllowed() {
        PlayerAction playerAction = new AbstractPlayerActionImpl() {
            @Override
            public boolean isActionAllowed(final Player player) {
                return false;
            }
        };
        Player player = new Player("Test", 100, p -> { }, new Hand(5), new Field(5), new Deck(Arrays.asList(new MonsterCard("Test", 5, 5, MonsterModus.HEALING))), new Graveyard());
        playerAction.performAction(player);
    }
    
    private class AbstractPlayerActionImpl extends AbstractPlayerAction {

        @Override
        public boolean isActionAllowed(final Player player) {
            return true;
        }

        @Override
        protected void internalPerformAction(final Player player) {
            counter.incrementAndGet();
        }
    }
}