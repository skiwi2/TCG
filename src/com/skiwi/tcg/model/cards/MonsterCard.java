
package com.skiwi.tcg.model.cards;

import com.skiwi.tcg.model.objects.MonsterModus;
import com.skiwi.tcg.utils.ExceptionUtils;
import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public final class MonsterCard implements Card {
    private final String name;
    private final int attack;
    private final int maximumHitpoints;
    private int hitpoints;
    private MonsterModus modus;
    
    public MonsterCard(final String name, final int attack, final int hitpoints, final MonsterModus modus) {
        this.name = name;
        this.attack = attack;
        this.maximumHitpoints = hitpoints;
        this.hitpoints = hitpoints;
        this.modus = Objects.requireNonNull(modus);
    }
    
    public int increaseHitpoints(final int increment) {
        ExceptionUtils.throwOnFail(increment >= 0, IllegalArgumentException::new, "increment cannot be negative");
        int oldHitpoints = hitpoints;
        hitpoints = (hitpoints + increment >= maximumHitpoints) ? maximumHitpoints : hitpoints + increment;
        return hitpoints - oldHitpoints;
    }
    
    public int decreaseHitpoints(final int decrement) {
        ExceptionUtils.throwOnFail(decrement <= 0, IllegalArgumentException::new, "decrement cannot be positive");
        int oldHitpoints = hitpoints;
        hitpoints = (hitpoints - decrement <= 0) ? 0 : hitpoints - decrement;
        return hitpoints - oldHitpoints;
    }
    
    public void setModus(final MonsterModus modus) {
        this.modus = Objects.requireNonNull(modus);
    }

    @Override
    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public MonsterModus getModus() {
        return modus;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + this.attack;
        hash = 73 * hash + this.maximumHitpoints;
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
        final MonsterCard other = (MonsterCard)obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.attack != other.attack) {
            return false;
        }
        if (this.maximumHitpoints != other.maximumHitpoints) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "MonsterCard(" + name + ", " + attack + ", " + maximumHitpoints + "," + hitpoints + "," + modus + ")" ;
    }
}
