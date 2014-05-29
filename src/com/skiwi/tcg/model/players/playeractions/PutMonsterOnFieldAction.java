
package com.skiwi.tcg.model.players.playeractions;

import com.skiwi.tcg.model.cards.MonsterCard;
import com.skiwi.tcg.model.objects.Field;
import com.skiwi.tcg.model.objects.Hand;
import com.skiwi.tcg.model.players.AbstractPlayerAction;
import com.skiwi.tcg.model.players.Player;
import com.skiwi.tcg.model.players.PlayerAction;
import com.skiwi.tcg.utils.Arguments;
import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public class PutMonsterOnFieldAction extends AbstractPlayerAction implements PlayerAction {
    private final int handCardIndex;
    private final int fieldMonsterIndex;

    public PutMonsterOnFieldAction(final int handCardIndex, final int fieldMonsterIndex) {
        this.handCardIndex = Arguments.requirePositiveOrZero(handCardIndex, "handCardIndex");
        this.fieldMonsterIndex = Arguments.requirePositiveOrZero(fieldMonsterIndex, "fieldCardIndex");
    }

    @Override
    public boolean isActionAllowed(final Player player) {
        Objects.requireNonNull(player, "player");
        Hand hand = player.getHand();
        Field field = player.getField();
        if (handCardIndex >= hand.getCapacity()) {
            return false;
        }
        if (fieldMonsterIndex >= field.getMonsterCapacity()) {
            return false;
        }
        if (field.hasMonster(fieldMonsterIndex)) {
            return false;
        }
        if (!(hand.get(handCardIndex) instanceof MonsterCard)) {
            return false;
        }
        return true;
    }

    @Override
    public void internalPerformAction(final Player player) {
        Objects.requireNonNull(player);
        Hand hand = player.getHand();
        Field field = player.getField();
        field.setMonster(fieldMonsterIndex, (MonsterCard)hand.play(handCardIndex));
    }
}
