
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.FusionCard;
import com.skiwi.tcg.model.cards.MonsterCard;
import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public enum FusionStat implements MonsterStatBuilder {
    ATTACK {
        @Override
        public MonsterCard.MonsterCardBuilder createBuilder(final MonsterCard baseMonsterCard, final MonsterCard fusedMonsterCard, final FusionCard fusionCard) {
            assertArguments(baseMonsterCard, fusedMonsterCard, fusionCard);
            return new MonsterCard.MonsterCardBuilder(baseMonsterCard)
                    .attack((int)(baseMonsterCard.getAttack() + (fusionCard.getFusionPower() / 100d * fusedMonsterCard.getAttack())));
        }
    },

    HITPOINTS {
        @Override
        public MonsterCard.MonsterCardBuilder createBuilder(final MonsterCard baseMonsterCard, final MonsterCard fusedMonsterCard, final FusionCard fusionCard) {
            assertArguments(baseMonsterCard, fusedMonsterCard, fusionCard);
            return new MonsterCard.MonsterCardBuilder(baseMonsterCard)
                    .maximumHitpoints((int)(baseMonsterCard.getMaximumHitpoints() + (fusionCard.getFusionPower() / 100d * fusedMonsterCard.getMaximumHitpoints())))
                    .hitpoints((int)(baseMonsterCard.getHitpoints() + (fusionCard.getFusionPower() / 100d * fusedMonsterCard.getHitpoints())));
        }
    };

    private static void assertArguments(final MonsterCard baseMonsterCard, final MonsterCard fusedMonsterCard, final FusionCard fusionCard) {
        Objects.requireNonNull(baseMonsterCard);
        Objects.requireNonNull(fusedMonsterCard);
        Objects.requireNonNull(fusionCard);
        if (baseMonsterCard.equals(fusedMonsterCard)) {
            throw new IllegalArgumentException("baseMonsterCard and fusedMonsterCard need to be different");
        }
    }
}

@FunctionalInterface
interface MonsterStatBuilder {
    MonsterCard.MonsterCardBuilder createBuilder(final MonsterCard baseMonsterCard, final MonsterCard fusedMonsterCard, final FusionCard fusionCard);
}
