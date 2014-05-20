
package com.skiwi.tcg.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class StatesTest {
    static {
        assertTrue(true);
    }
    
    /** States.requireTrue **/

    @Test
    public void testRequireTrue_boolean() {
        assertTrue(States.requireTrue(true));
    }
    
    @Test(expected = IllegalStateException.class)
    public void testRequireTrue_booleanISE() {
        States.requireTrue(false);
    }

    @Test
    public void testRequireTrue_boolean_String() {
        assertTrue(States.requireTrue(true, "test"));
    }
    
    @Test(expected = IllegalStateException.class)
    public void testRequireTrue_boolean_StringISE() {
        States.requireTrue(false, "test");
    }
    
    @Test(expected = NullPointerException.class)
    public void testRequireTrue_boolean_StringNPE() {
        States.requireTrue(true, null);
    }
    
    /** States.requireFalse **/

    @Test
    public void testRequireFalse_boolean() {
        assertFalse(States.requireFalse(false));
    }
    
    @Test(expected = IllegalStateException.class)
    public void testRequireFalse_booleanISE() {
        States.requireFalse(true);
    }

    @Test
    public void testRequireFalse_boolean_String() {
        assertFalse(States.requireFalse(false, "test"));
    }
    
    @Test(expected = IllegalStateException.class)
    public void testRequireFalse_boolean_StringISE() {
        States.requireFalse(true, "test");
    }
    
    @Test(expected = NullPointerException.class)
    public void testRequireFalse_boolean_StringNPE() {
        States.requireFalse(false, null);
    }
    
    /** States.requireEmpty **/

    @Test
    public void testRequireEmpty_GenericType() {
        Collection<Integer> c = Arrays.asList();
        assertEquals(c, States.requireEmpty(c));
    }

    @Test(expected = IllegalStateException.class)
    public void testRequireEmpty_GenericTypeISE() {
        States.requireEmpty(Arrays.asList(2, 5));
    }

    @Test
    public void testRequireEmpty_GenericType_String() {
        Collection<Integer> c = Arrays.asList();
        assertEquals(c, States.requireEmpty(c, "test"));
    }

    @Test(expected = IllegalStateException.class)
    public void testRequireEmpty_GenericType_StringISE() {
        States.requireEmpty(Arrays.asList(2, 5), "test");
    }
    
    @Test(expected = NullPointerException.class)
    public void testRequireEmpty_GenericType_StringNPE() {
        States.requireEmpty(Arrays.asList(), null);
    }
    
    /** States.requireNonEmpty **/

    @Test
    public void testRequireNonEmpty_GenericType() {
        Collection<Integer> c = Arrays.asList(2, 5);
        assertEquals(c, States.requireNonEmpty(c));
    }

    @Test(expected = NoSuchElementException.class)
    public void testRequireNonEmpty_GenericTypeISE() {
        States.requireNonEmpty(Arrays.asList());
    }

    @Test
    public void testRequireNonEmpty_GenericType_String() {
        Collection<Integer> c = Arrays.asList(2, 5);
        assertEquals(c, States.requireNonEmpty(c, "test"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testRequireNonEmpty_GenericType_StringISE() {
        States.requireNonEmpty(Arrays.asList(), "test");
    }
    
    @Test(expected = NullPointerException.class)
    public void testRequireNonEmpty_GenericType_StringNPE() {
        States.requireNonEmpty(Arrays.asList(2, 5), null);
    }
    
    /** States.requireNull **/
    
    @Test
    public void testRequireNull_2argsNull() {
        assertEquals((Object)null, States.requireNull(null, CustomException::new));
    }
    
    @Test(expected = CustomException.class)
    public void testRequireNull_2argsNonNull() {
        States.requireNull(new Object(), CustomException::new);
    }
    
    @Test(expected = NullPointerException.class)
    public void testRequireNull_2argsSupplierNull() {
        States.requireNull(null, null);
    }
    
    @Test
    public void testRequireNull_3argsNull() {
        Object object = new Object();
        assertEquals((Object)null, States.requireNull(null, CustomException::new, "custom message"));
    }
    
    @Test
    public void testRequireNull_3argsNonNull() {
        try {
            States.requireNull(new Object(), CustomException::new, "custom message");
        } catch (CustomException ex) {
            assertEquals("custom message", ex.getMessage());
        }
    }
    
    @Test(expected = NullPointerException.class)
    public void testRequireNull_3argsSupplierNull() {
        States.requireNull(null, null, "custom message");
    }
    
    @Test(expected = NullPointerException.class)
    public void testRequireNull_3argsMessageNull() {
        States.requireNull(null, CustomException::new, null);
    }
    
    /** States.requireNonNull **/
    
    @Test
    public void testRequireNonNull_2argsNonNull() {
        Object object = new Object();
        assertEquals(object, States.requireNonNull(object, CustomException::new));
    }
    
    @Test(expected = CustomException.class)
    public void testRequireNonNull_2argsNull() {
        States.requireNonNull(null, CustomException::new);
    }
    
    @Test(expected = NullPointerException.class)
    public void testRequireNonNull_2argsSupplierNull() {
        States.requireNonNull(new Object(), null);
    }
    
    @Test
    public void testRequireNonNull_3argsNonNull() {
        Object object = new Object();
        assertEquals(object, States.requireNonNull(object, CustomException::new, "custom message"));
    }
    
    @Test
    public void testRequireNonNull_3argsNull() {
        try {
            States.requireNonNull(null, CustomException::new, "custom message");
        } catch (CustomException ex) {
            assertEquals("custom message", ex.getMessage());
        }
    }
    
    @Test(expected = NullPointerException.class)
    public void testRequireNonNull_3argsSupplierNull() {
        States.requireNonNull(new Object(), null, "custom message");
    }
    
    @Test(expected = NullPointerException.class)
    public void testRequireNonNull_3argsMessageNull() {
        States.requireNonNull(new Object(), CustomException::new, null);
    }
    
    @SuppressWarnings("serial")
    private static class CustomException extends RuntimeException {
        public CustomException() {
            super();
        }
        
        public CustomException(final String message) {
            super(message);
        }
    }
}