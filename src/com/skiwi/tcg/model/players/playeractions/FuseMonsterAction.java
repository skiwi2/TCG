
package com.skiwi.tcg.model.players.playeractions;

import com.skiwi.tcg.model.cards.FusionCard;
import com.skiwi.tcg.model.cards.MonsterCard;
import com.skiwi.tcg.model.objects.Field;
import com.skiwi.tcg.model.objects.Graveyard;
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
public class FuseMonsterAction extends AbstractPlayerAction implements PlayerAction {
    private final int fusionCardIndex;
    private final int baseMonsterCardIndex;
    private final int fuserMonsterCardIndex;

    public FuseMonsterAction(final int fusionCardIndex, final int baseMonsterCardIndex, final int fuserMonsterCardIndex) {
        Arguments.requirePositiveOrZero(baseMonsterCardIndex, "baseMonsterCardIndex");
        Arguments.requirePositiveOrZero(fuserMonsterCardIndex, "fuserMonsterCardIndex");
        if (baseMonsterCardIndex == fuserMonsterCardIndex) {
            throw new IllegalArgumentException("baseMonsterCardIndex == fuserMonsterCardIndex");
        }
        this.fusionCardIndex = Arguments.requirePositiveOrZero(fusionCardIndex, "fusionCardIndex");
        this.baseMonsterCardIndex = baseMonsterCardIndex;
        this.fuserMonsterCardIndex = fuserMonsterCardIndex;
    }
    
    @Override
    public boolean isActionAllowed(final Player player) {
        Objects.requireNonNull(player, "player");
        Hand hand = player.getHand();
        Field field = player.getField();
        if (fusionCardIndex >= hand.getCapacity()) {
            return false;
        }
        if (baseMonsterCardIndex >= field.getMonsterCapacity()) {
            return false;
        }
        if (fuserMonsterCardIndex >= field.getMonsterCapacity()) {
            return false;
        }
        if (!(hand.get(fusionCardIndex) instanceof FusionCard)) {
            return false;
        }
        if (!field.hasMonster(baseMonsterCardIndex)) {
            return false;
        }
        if (!field.hasMonster(fuserMonsterCardIndex)) {
            return false;
        }
        return true;
    }

    @Override
    protected void internalPerformAction(final Player player) {
        Objects.requireNonNull(player);
        Hand hand = player.getHand();
        Field field = player.getField();
        Graveyard graveyard = player.getGraveyard();
        FusionCard fusionCard = (FusionCard)hand.play(fusionCardIndex);
        MonsterCard baseMonsterCard = field.destroyMonster(baseMonsterCardIndex);
        MonsterCard fuserMonsterCard = field.destroyMonster(fuserMonsterCardIndex);
        MonsterCard fusedCard = baseMonsterCard.fuseWith(fuserMonsterCard, fusionCard);
        graveyard.add(baseMonsterCard);
        graveyard.add(fuserMonsterCard);
        graveyard.add(fusionCard);
        field.setMonster(baseMonsterCardIndex, fusedCard);
    }
}
