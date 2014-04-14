
package com.skiwi.tcg.model.cards;

/**
 *
 * @author Frank van Heeswijk
 */
public class FusionCard {
    private final String name;
    private final int fusionPower;
    private final int fusionStat;

    public FusionCard(final String name, final int fusionPower, final int fusionStat) {
        this.name = name;
        this.fusionPower = fusionPower;
        this.fusionStat = fusionStat;
    }

    public String getName() {
        return name;
    }

    public int getFusionPower() {
        return fusionPower;
    }

    public int getFusionStat() {
        return fusionStat;
    }
}
