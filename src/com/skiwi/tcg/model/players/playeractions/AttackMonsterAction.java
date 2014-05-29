
package com.skiwi.tcg.model.players.playeractions;

import com.skiwi.tcg.model.cards.AttackResult;
import com.skiwi.tcg.model.cards.MonsterCard;
import com.skiwi.tcg.model.objects.Field;
import com.skiwi.tcg.model.objects.MonsterModus;
import com.skiwi.tcg.model.players.AbstractPlayerAction;
import com.skiwi.tcg.model.players.Player;
import com.skiwi.tcg.model.players.PlayerAction;
import com.skiwi.tcg.utils.Arguments;
import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public class AttackMonsterAction extends AbstractPlayerAction implements PlayerAction {
    private final int monsterIndex;
    private final int targetMonsterIndex;
    private final Player targetPlayer;
    
    public AttackMonsterAction(final int monsterIndex, final int targetMonsterIndex, final Player targetPlayer) {
        this.monsterIndex = Arguments.requirePositiveOrZero(monsterIndex, "monsterIndex");
        this.targetMonsterIndex = targetMonsterIndex;
        this.targetPlayer = Objects.requireNonNull(targetPlayer, "targetPlayer");
        Arguments.requireInRange(targetMonsterIndex, 0, targetPlayer.getField().getMonsterCapacity(), "targetMonsterIndex");
    }
    
    @Override
    public boolean isActionAllowed(final Player player) {
        Objects.requireNonNull(player, "player");
        Field field = player.getField();
        Field targetField = targetPlayer.getField();
        if (monsterIndex >= field.getMonsterCapacity()) {
            return false;
        }
        if (!field.hasMonster(monsterIndex)) {
            return false;
        }
        if (!targetField.hasMonster(targetMonsterIndex)) {
            return false;
        }
        if (field.getMonster(monsterIndex).getModus() != MonsterModus.OFFENSIVE) {
            return false;
        }
        return true;
    }
    
    @Override
    protected void internalPerformAction(final Player player) {
        Objects.requireNonNull(player);
        Field field = player.getField();
        Field targetField = targetPlayer.getField();
        MonsterCard monster = field.getMonster(monsterIndex);
        MonsterCard targetMonster = targetField.getMonster(monsterIndex);
        AttackResult attackResult = monster.attack(targetMonster);
        if (attackResult == AttackResult.TARGET_DIES) {
            targetField.destroyMonster(targetMonsterIndex);
        }
    }
}
