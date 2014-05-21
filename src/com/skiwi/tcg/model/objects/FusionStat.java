
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.FusionCard;
import com.skiwi.tcg.model.cards.MonsterCard;
import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public enum FusionStat implements MonsterStatBuilderInterface {
    ATTACK {
        @Override
        public MonsterCard.MonsterCardBuilder createBuilder(final MonsterCard baseMonsterCard, final MonsterCard fusedMonsterCard, final FusionCard fusionCard) {
            Objects.requireNonNull(baseMonsterCard);
            Objects.requireNonNull(fusedMonsterCard);
            Objects.requireNonNull(fusionCard);
            return new MonsterCard.MonsterCardBuilder(baseMonsterCard)
                    .attack(baseMonsterCard.getAttack() + (fusionCard.getFusionPower() * fusedMonsterCard.getAttack()));
        }
    },

    HITPOINTS {
        @Override
        public MonsterCard.MonsterCardBuilder createBuilder(final MonsterCard baseMonsterCard, final MonsterCard fusedMonsterCard, final FusionCard fusionCard) {
            Objects.requireNonNull(baseMonsterCard);
            Objects.requireNonNull(fusedMonsterCard);
            Objects.requireNonNull(fusionCard);
            return new MonsterCard.MonsterCardBuilder(baseMonsterCard)
                    .hitpoints(baseMonsterCard.getHitpoints() + (fusionCard.getFusionPower() * fusedMonsterCard.getHitpoints()));
        }
    };
}

@FunctionalInterface
interface MonsterStatBuilderInterface {
    public MonsterCard.MonsterCardBuilder createBuilder(final MonsterCard baseMonsterCard, final MonsterCard fusedMonsterCard, final FusionCard fusionCard);
}