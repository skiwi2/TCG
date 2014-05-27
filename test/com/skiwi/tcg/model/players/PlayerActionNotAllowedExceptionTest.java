
package com.skiwi.tcg.model.players;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class PlayerActionNotAllowedExceptionTest {
    static {
        assertTrue(true);
    }
    
    @Test
    public void testConstructor() {
        new PlayerActionNotAllowedException();
    }
    
    @Test
    public void testConstructorMessage() {
        PlayerActionNotAllowedException exception = new PlayerActionNotAllowedException("Test");
        assertEquals("Test", exception.getMessage());
    }
    
    @Test
    public void testConstructorMessageCause() {
        Throwable cause = new Throwable();
        PlayerActionNotAllowedException exception = new PlayerActionNotAllowedException("Test", cause);
        assertEquals("Test", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
    
    @Test
    public void testConstructorCause() {
        Throwable cause = new Throwable();
        PlayerActionNotAllowedException exception = new PlayerActionNotAllowedException(cause);
        assertEquals(cause, exception.getCause());
    }
}