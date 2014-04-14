
package com.skiwi.tcg.model.players;

import com.skiwi.tcg.model.cards.MonsterCard;
import com.skiwi.tcg.model.objects.Deck;
import com.skiwi.tcg.model.objects.Graveyard;
import com.skiwi.tcg.model.objects.Hand;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Frank van Heeswijk
 */
public class Player {
    private final Hand hand;
    private final List<MonsterCard> fieldMonsters = new ArrayList<>();
    private final Deck deck = new Deck();
    private final Graveyard graveyard = new Graveyard();
    
    public Player(final Hand hand) {
        this.hand = hand;
    }
}
