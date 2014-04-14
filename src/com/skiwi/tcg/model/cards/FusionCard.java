
package com.skiwi.tcg.model.cards;

/**
 *
 * @author Frank van Heeswijk
 */
public class FusionCard implements Card {
    private final String name;
    private final int fusionPower;
    private final int fusionStat;

    public FusionCard(final String name, final int fusionPower, final int fusionStat) {
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

    public int getFusionStat() {
        return fusionStat;
    }
}
