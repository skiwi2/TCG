
package com.skiwi.tcg.model.players;

import java.util.Objects;

/**
 *
 * @author Frank van Heeswijk
 */
public abstract class AbstractPlayerAction implements PlayerAction {
    @Override
    abstract public boolean isActionAllowed(final Player player);

    @Override
    public void performAction(final Player player) throws PlayerActionNotAllowedException {
        Objects.requireNonNull(player, "player");
        if (!isActionAllowed(player)) {
            throw new PlayerActionNotAllowedException();
        }
        internalPerformAction(player);
    }
    
    abstract protected void internalPerformAction(final Player player);
}
