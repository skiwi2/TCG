
package com.skiwi.tcg.model.players;

/**
 *
 * @author Frank van Heeswijk
 */
public class PlayerActionNotAllowed extends RuntimeException {
    private static final long serialVersionUID = 4656594949564649L;

    public PlayerActionNotAllowed() {
        super();
    }
    
    public PlayerActionNotAllowed(final String message) {
        super(message);
    }
    
    public PlayerActionNotAllowed(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public PlayerActionNotAllowed(final Throwable cause) {
        super(cause);
    }
}
