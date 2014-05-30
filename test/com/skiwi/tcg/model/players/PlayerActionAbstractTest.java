
package com.skiwi.tcg.model.players;

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

    private PlayerAction playerAction;
    
    protected abstract PlayerAction supplyPlayerAction();

    @Before
    final public void beforePlayerActionAbstractTest() {
        playerAction = supplyPlayerAction();
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
