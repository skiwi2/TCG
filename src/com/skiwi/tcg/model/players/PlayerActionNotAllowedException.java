
package com.skiwi.tcg.model.players;

/**
 *
 * @author Frank van Heeswijk
 */
public class PlayerActionNotAllowedException extends RuntimeException {
    private static final long serialVersionUID = 4656594949564649L;

    public PlayerActionNotAllowedException() {
        super();
    }
    
    public PlayerActionNotAllowedException(final String message) {
        super(message);
    }
    
    public PlayerActionNotAllowedException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public PlayerActionNotAllowedException(final Throwable cause) {
        super(cause);
    }
}
