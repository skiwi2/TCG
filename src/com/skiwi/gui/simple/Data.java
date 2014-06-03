
package com.skiwi.gui.simple;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.cards.FusionCard;
import com.skiwi.tcg.model.cards.MonsterCard;
import com.skiwi.tcg.model.objects.FusionStat;
import com.skiwi.tcg.model.objects.MonsterModus;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author Frank van Heeswijk
 */
public final class Data {
    private Data() {
        throw new UnsupportedOperationException();
    }
    
    public static final Collection<Card> CARDS = Arrays.asList(
        new MonsterCard("Phoenix", 10, 10, MonsterModus.HEALING),
        new MonsterCard("Iron Slug", 4, 25, MonsterModus.HEALING),
        new FusionCard("Power Up", 20, FusionStat.ATTACK),
        new MonsterCard("Fire Imp", 5, 10, MonsterModus.HEALING),
        new MonsterCard("Craxy Ox", 15, 15, MonsterModus.HEALING),
        new FusionCard("Live Up", 10, FusionStat.HITPOINTS),
        new MonsterCard("Engineer", 10, 10, MonsterModus.HEALING),
        new MonsterCard("Mechanized Cannon", 30, 5, MonsterModus.HEALING),
        new FusionCard("Extra Power", 50, FusionStat.ATTACK),
        new MonsterCard("Dragon", 12, 18, MonsterModus.HEALING),
        new MonsterCard("Electrocuter", 3, 100, MonsterModus.HEALING)
    );
}
