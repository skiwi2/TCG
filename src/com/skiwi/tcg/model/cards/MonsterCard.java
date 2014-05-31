
package com.skiwi.tcg.model.cards;

import com.skiwi.tcg.model.objects.MonsterModus;
import com.skiwi.tcg.utils.Arguments;
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
    
    public MonsterCard(final String name, final int attack, final int maximumHitpoints, final MonsterModus modus) {
        Objects.requireNonNull(name);
        Arguments.requireMinimalLength(name, 1, "name");
        Arguments.requirePositive(maximumHitpoints, "maximum hitpoints");
        this.name = Objects.requireNonNull(name);
        this.attack = Arguments.requirePositiveOrZero(attack, "attack");
        this.maximumHitpoints = maximumHitpoints;
        this.hitpoints = maximumHitpoints;
        this.modus = Objects.requireNonNull(modus);
    }
    
    public int increaseHitpoints(final int increment) {
        Arguments.requirePositiveOrZero(increment, "increment");
        int oldHitpoints = hitpoints;
        hitpoints = (hitpoints + increment >= maximumHitpoints) ? maximumHitpoints : hitpoints + increment;
        return hitpoints - oldHitpoints;
    }
    
    public int decreaseHitpoints(final int decrement) {
        Arguments.requirePositiveOrZero(decrement, "decrement");
        int oldHitpoints = hitpoints;
        hitpoints = (hitpoints - decrement <= 0) ? 0 : hitpoints - decrement;
        return oldHitpoints - hitpoints;
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
    
    public int getMaximumHitpoints() {
        return maximumHitpoints;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public MonsterModus getModus() {
        return modus;
    }
    
    public boolean isDead() {
        return (hitpoints <= 0);
    }
    
    public MonsterCard fuseWith(final MonsterCard fuserMonsterCard, final FusionCard fusionCard) {
        Objects.requireNonNull(fuserMonsterCard);
        Objects.requireNonNull(fusionCard);
        if (fuserMonsterCard.equals(this)) {
            throw new IllegalArgumentException("it is not possible to fuse with yourself");
        }
        return fusionCard.getFusionStat().createBuilder(this, fuserMonsterCard, fusionCard)
                .name(getName() + " " + fuserMonsterCard.getName())
                .build();
    }
    
    public AttackResult attack(final MonsterCard target) {
        Objects.requireNonNull(target);
        if (getModus() != MonsterModus.OFFENSIVE) {
            throw new IllegalStateException("cannot attack: modus = " + getModus());
        }
        target.decreaseHitpoints(this.getAttack());
        return (target.isDead()) ? AttackResult.TARGET_DIES : AttackResult.TARGET_SURVIVES;
    }
    
    void setHitpoints(final int hitpoints) {
        Arguments.requireInRangeClosed(hitpoints, 1, maximumHitpoints, "hitpoints");
        this.hitpoints = hitpoints;
    }
    
    @Override
    public String toString() {
        return MonsterCard.class.getSimpleName() + "(" + name + ", " + attack + ", " + maximumHitpoints + ", " + hitpoints + ", " + modus + ")" ;
    }
}
