
package com.skiwi.tcg.model.cards;

import com.skiwi.tcg.model.objects.FusionStat;
import com.skiwi.tcg.utils.Arguments;
import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public final class FusionCard implements Card {
    private final String name;
    private final int fusionPower;
    private final FusionStat fusionStat;

    public FusionCard(final String name, final int fusionPower, final FusionStat fusionStat) {
        Objects.requireNonNull(name);
        Arguments.requireMinimalLength(name, 1, "name");
        Arguments.requirePositive(fusionPower, "fusion power");
        Objects.requireNonNull(fusionStat);
        this.name = name;
        this.fusionPower = fusionPower;
        this.fusionStat = fusionStat;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getFusionPower() {
        return fusionPower;
    }

    public FusionStat getFusionStat() {
        return fusionStat;
    }
    
    @Override
    public String toString() {
        return FusionCard.class.getSimpleName() + "(" + name + ", " + fusionPower + ", " + fusionStat + ")";
    }
}
