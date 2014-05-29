
package com.skiwi.tcg.model.players.playeractions;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.objects.Deck;
import com.skiwi.tcg.model.objects.Graveyard;
import com.skiwi.tcg.model.objects.Hand;
import com.skiwi.tcg.model.players.Player;
import com.skiwi.tcg.model.players.PlayerAction;
import com.skiwi.tcg.model.players.PlayerActionNotAllowedException;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author Frank van Heeswijk
 */
public class DrawCardAction implements PlayerAction {
    @Override
    public boolean isActionAllowed(final Player player) {
        Objects.requireNonNull(player);
        Deck deck = player.getDeck();
        Graveyard graveyard = player.getGraveyard();
        Hand hand = player.getHand();
        if (deck.isEmpty() && graveyard.isEmpty()) {
            return false;
        }
        if (hand.isFull()) {
            return false;
        }
        return true;
    }
    
    @Override
    public void performAction(final Player player) {
        Objects.requireNonNull(player);
        if (!isActionAllowed(player)){
            throw new PlayerActionNotAllowedException();
        }
        Deck deck = player.getDeck();
        Hand hand = player.getHand();
        Graveyard graveyard = player.getGraveyard();
        Optional<Card> optionalCard = deck.take();
        if (optionalCard.isPresent()) {
            hand.add(optionalCard.get());
        }
        else {
            graveyard.transferAll(deck);
            deck.shuffle();
            hand.add(deck.take().orElseThrow(() -> new IllegalStateException("deck and graveyard are empty")));
        }
    }
}
