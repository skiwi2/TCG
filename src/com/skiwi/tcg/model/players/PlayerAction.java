
package com.skiwi.tcg.model.players;

/**
 *
 * @author Frank van Heeswijk
 */
public interface PlayerAction {
    boolean isActionAllowed(final Player player);
    
    void performAction(final Player player) throws PlayerActionNotAllowedException;
}
