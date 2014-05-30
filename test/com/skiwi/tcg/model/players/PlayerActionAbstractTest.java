
package com.skiwi.tcg.model.players;

import java.util.Objects;
import java.util.function.Supplier;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
@Ignore
public abstract class PlayerActionAbstractTest {
    static {
        assertTrue(true);
    }

    private final Supplier<PlayerAction> playerActionSupplier;

    private PlayerAction playerAction;

    public PlayerActionAbstractTest() {
        this.playerActionSupplier = null;
    }

    protected PlayerActionAbstractTest(final Supplier<PlayerAction> playerActionSupplier) {
        this.playerActionSupplier = Objects.requireNonNull(playerActionSupplier, "playerActionSupplier");
    }

    @Before
    final public void beforePlayerActionAbstractTest() {
        playerAction = playerActionSupplier.get();
    }

    @Test(expected = NullPointerException.class)
    public void testIsActionAllowedNullPlayer() {
        playerAction.isActionAllowed(null);
    }

    @Test(expected = NullPointerException.class)
    public void testPerformActionNullPlayer() {
        playerAction.performAction(null);
    }
}
