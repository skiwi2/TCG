
package com.skiwi.tcg.model.cards;

/**
 *
 * @author Frank van Heeswijk
 */
public class MonsterCard implements Card {
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
}
