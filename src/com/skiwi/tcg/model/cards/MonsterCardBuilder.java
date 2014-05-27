
package com.skiwi.tcg.model.cards;

import com.skiwi.tcg.model.objects.MonsterModus;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 *
 * @author Frank van Heeswijk
 */
public class MonsterCardBuilder {
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
        name(source.getName()).attack(source.getAttack()).maximumHitpoints(source.getMaximumHitpoints()).hitpoints(source.getHitpoints()).modus(source.getModus());
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
