
package com.skiwi.tcg.model.cards;

import com.skiwi.tcg.model.objects.MonsterModus;
import com.skiwi.tcg.utils.Arguments;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

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
        return (hitpoints == 0);
    }
    
    public MonsterCard fuseWith(final MonsterCard fuserMonsterCard, final FusionCard fusionCard) {
        Objects.requireNonNull(fuserMonsterCard);
        Objects.requireNonNull(fusionCard);
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
    
    private void setHitpoints(final int hitpoints) {
        Arguments.requireInRangeClosed(hitpoints, 1, maximumHitpoints, "hitpoints");
        this.hitpoints = hitpoints;
    }
    
    @Override
    public String toString() {
        return MonsterCard.class.getSimpleName() + "(" + name + ", " + attack + ", " + maximumHitpoints + ", " + hitpoints + ", " + modus + ")" ;
    }
    
    public static class MonsterCardBuilder {
        private Optional<String> name = Optional.empty();
        private OptionalInt attack = OptionalInt.empty();
        private OptionalInt maximumHitpoints = OptionalInt.empty();
        private OptionalInt hitpoints = OptionalInt.empty();
        private Optional<MonsterModus> modus = Optional.empty();
        
        public MonsterCardBuilder() { }
        
        public MonsterCardBuilder(final MonsterCard source) {
            copyFromSource(Objects.requireNonNull(source));
        }
        
        private void copyFromSource(final MonsterCard source) {
            Objects.requireNonNull(source);
            name(source.getName())
                    .attack(source.getAttack())
                    .maximumHitpoints(source.getMaximumHitpoints())
                    .hitpoints(source.getHitpoints())
                    .modus(source.getModus());
        }
        
        public MonsterCardBuilder name(final String name) {
            this.name = Optional.of(name);
            return this;
        }
        
        public MonsterCardBuilder attack(final int attack) {
            this.attack = OptionalInt.of(attack);
            return this;
        }
        
        public MonsterCardBuilder maximumHitpoints(final int maximumHitpoints) {
            this.maximumHitpoints = OptionalInt.of(maximumHitpoints);
            return this;
        }
        
        public MonsterCardBuilder hitpoints(final int hitpoints) {
            this.hitpoints = OptionalInt.of(hitpoints);
            return this;
        }
        
        public MonsterCardBuilder modus(final MonsterModus modus) {
            this.modus = Optional.of(modus);
            return this;
        }
        
        public MonsterCard build() {
            if (!name.isPresent() || !attack.isPresent() || !maximumHitpoints.isPresent() || !modus.isPresent()) {
                throw new IllegalStateException("The fields name, attack, maximum hitpoints and modus are required");
            }
            MonsterCard monsterCard = new MonsterCard(name.get(), attack.getAsInt(), maximumHitpoints.getAsInt(), modus.get());
            if (hitpoints.isPresent()) {
                monsterCard.setHitpoints(hitpoints.getAsInt());
            }
            return monsterCard;
        }
    }
}
