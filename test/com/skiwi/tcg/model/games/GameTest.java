
package com.skiwi.tcg.model.games;

import com.skiwi.tcg.model.cards.MonsterCard;
import com.skiwi.tcg.model.objects.MonsterModus;
import com.skiwi.tcg.model.players.Player;
import com.skiwi.tcg.model.players.PlayerConfiguration;
import com.skiwi.tcg.model.players.PlayerConfigurationBuilder;
import com.skiwi.tcg.model.players.TurnAction;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class GameTest {
    static {
        assertTrue(true);
    }
    
    private final PlayerConfiguration playerConfiguration = new PlayerConfigurationBuilder()
            .turnAction(new TurnActionImpl())
            .handCapacity(5)
            .fieldMonsterCapacity(5)
            .deckCards(Arrays.asList(new MonsterCard("Test", 5, 5, MonsterModus.HEALING)))
            .build();
    
    @Test
    public void testSetSelf() {
        Game game = new Game();
        game.setSelf(Player.createFromConfiguration(playerConfiguration, "Test"));
    }
    
    @Test(expected = NullPointerException.class)
    public void testSetSelfNullSelf() {
        Game game = new Game();
        game.setSelf(null);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testSetSelfAlreadySetAsSelf() {
        Game game = new Game();
        Player player = Player.createFromConfiguration(playerConfiguration, "Test");
        game.setSelf(player);
        game.setSelf(player);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testSetSelfAlreadySetAsOpponent() {
        Game game = new Game();
        Player player = Player.createFromConfiguration(playerConfiguration, "Test");
        game.setOpponent(player);
        game.setSelf(player);
    }
    
    @Test
    public void testSetOpponent() {
        Game game = new Game();
        game.setOpponent(Player.createFromConfiguration(playerConfiguration, "Test"));
    }
    
    @Test(expected = NullPointerException.class)
    public void testSetOpponentNullOpponent() {
        Game game = new Game();
        game.setOpponent(null);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testSetOpponentAlreadySetAsOpponent() {
        Game game = new Game();
        Player player = Player.createFromConfiguration(playerConfiguration, "Test");
        game.setOpponent(player);
        game.setOpponent(player);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testSetOpponentAlreadySetAsSelf() {
        Game game = new Game();
        Player player = Player.createFromConfiguration(playerConfiguration, "Test");
        game.setSelf(player);
        game.setOpponent(player);
    }
    
    @Ignore
    @Test
    public void testPlay() {
        Game game = new Game();
        game.setSelf(Player.createFromConfiguration(playerConfiguration, "Self"));
        game.setOpponent(Player.createFromConfiguration(playerConfiguration, "Opponent"));
        game.play();
    }
    
    /** Extensive tests to get coverage on assertConstructed() done only once. **/
    
    @Test(expected = IllegalStateException.class)
    public void testPlayNotConstructedNullSelf() {
        Game game = new Game();
        game.setOpponent(Player.createFromConfiguration(playerConfiguration, "Opponent"));
        game.play();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testPlayNotConstructedNullOpponent() {
        Game game = new Game();
        game.setSelf(Player.createFromConfiguration(playerConfiguration, "Self"));
        game.play();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testPlayNotConstructedNullSelfAndNullOpponent() {
        Game game = new Game();
        game.play();
    }
    
    @Test
    public void testGetOpponentForSelf() {
        Game game = new Game();
        Player self = Player.createFromConfiguration(playerConfiguration, "Self");
        Player opponent = Player.createFromConfiguration(playerConfiguration, "Opponent");
        game.setSelf(self);
        game.setOpponent(opponent);
        assertEquals(opponent, game.getOpponentFor(self).get());
    }
    
    @Test
    public void testGetOpponentForOpponent() {
        Game game = new Game();
        Player self = Player.createFromConfiguration(playerConfiguration, "Self");
        Player opponent = Player.createFromConfiguration(playerConfiguration, "Opponent");
        game.setSelf(self);
        game.setOpponent(opponent);
        assertEquals(self, game.getOpponentFor(opponent).get());
    }
    
    @Test(expected = NullPointerException.class)
    public void testGetOpponentNullOpponent() {
        Game game = new Game();
        Player self = Player.createFromConfiguration(playerConfiguration, "Self");
        Player opponent = Player.createFromConfiguration(playerConfiguration, "Opponent");
        game.setSelf(self);
        game.setOpponent(opponent);
        game.getOpponentFor(null);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testGetOpponentNotConstructed() {
        Game game = new Game();
        game.getOpponentFor(Player.createFromConfiguration(playerConfiguration, "Test"));
    }
    
    @Test
    public void testGetPlayers() {
        Game game = new Game();
        Player self = Player.createFromConfiguration(playerConfiguration, "Self");
        Player opponent = Player.createFromConfiguration(playerConfiguration, "Opponent");
        game.setSelf(self);
        game.setOpponent(opponent);
        assertTrue(game.getPlayers().anyMatch(player -> player.equals(self)));
        assertTrue(game.getPlayers().anyMatch(player -> player.equals(opponent)));
    }
    
    @Test(expected = IllegalStateException.class)
    public void testGetPlayersNotConstructed() {
        Game game = new Game();
        game.getPlayers();
    }
    
    private static class TurnActionImpl implements TurnAction {
        @Override
        public void performTurn(final Player player) { }
    }
}