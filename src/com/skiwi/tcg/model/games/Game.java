
package com.skiwi.tcg.model.games;

import com.skiwi.tcg.model.players.Player;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author Frank van Heeswijk
 */
public class Game {
    private Player self;
    private Player opponent;
    
    private Optional<Player> activePlayer = Optional.empty();
    
    public void setSelf(final Player self) {
        Objects.requireNonNull(self);
        if (getPlayerStream().filter(x -> x.equals(self)).count() != 0) {
            throw new IllegalStateException("Player is already a player in this game: self = " + self);
        }
        this.self = self;
        self.setGame(this);
    }
    
    public void setOpponent(final Player opponent) {
        Objects.requireNonNull(opponent);
        if (getPlayerStream().filter(x -> x.equals(opponent)).count() != 0) {
            throw new IllegalStateException("Player is already a player in this game: opponent = " + opponent);
        }
        this.opponent = opponent;
        opponent.setGame(this);
    }
    
    public void play() {
        assertConstructed();
        while (!self.isDead() && !opponent.isDead()) {
            playTurn(self);
            playTurn(opponent);
        }
        activePlayer = Optional.empty();
    }
    
    private void playTurn(final Player player) {
        Objects.requireNonNull(player, "player");
        activePlayer = Optional.of(player);
        player.playTurn();
    }
    
    public Optional<Player> getOpponentFor(final Player player) {
        assertConstructed();
        Objects.requireNonNull(player, "player");
        return getPlayers().filter(x -> !x.equals(player)).findFirst();
    }
    
    private Stream<Player> getPlayerStream() {
        return Stream.of(self, opponent).filter(x -> x != null);
    }
    
    public Stream<Player> getPlayers() {
        assertConstructed();
        return getPlayerStream();
    }
    
    public Optional<Player> getActivePlayer() {
        assertConstructed();
        return activePlayer;
    }
    
    private void assertConstructed() {
        if (self == null || opponent == null) {
            throw new IllegalStateException("Game not properly constructed: self = " + self + " / opponent = " + opponent);
        }
    }
}
