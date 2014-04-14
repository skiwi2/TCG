
package com.skiwi.tcg.model.cards;

import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public final class MonsterCard implements Card {
    private final String name;
    private final int attack;
    private final int hitpoints;
    
    public MonsterCard(final String name, final int attack, final int hitpoints) {
        this.name = name;
        this.attack = attack;
        this.hitpoints = hitpoints;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + this.attack;
        hash = 73 * hash + this.hitpoints;
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
        if (this.hitpoints != other.hitpoints) {
            return false;
        }
        return true;
    }
}
