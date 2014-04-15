
package com.skiwi.tcg.model.cards;

import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public final class FusionCard implements Card {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + this.fusionPower;
        hash = 59 * hash + this.fusionStat;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FusionCard other = (FusionCard)obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.fusionPower != other.fusionPower) {
            return false;
        }
        if (this.fusionStat != other.fusionStat) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "FusionCard(" + name + ", " + fusionPower + ", " + fusionStat + ")";
    }
}
