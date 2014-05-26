
package com.skiwi.tcg.model.players;

/**
 *
 * @author Frank van Heeswijk
 */
@FunctionalInterface
public interface TurnAction {
    void performTurn(final Player player);
}
