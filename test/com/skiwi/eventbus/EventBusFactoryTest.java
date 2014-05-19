
package com.skiwi.eventbus;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class EventBusFactoryTest {
    static {
        assertTrue(true);
    }

    @Test
    public void testGetVoidEventBus() {
        assertNotNull(EventBusFactory.getVoidEventBus());
    }
}