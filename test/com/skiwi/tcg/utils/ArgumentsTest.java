
package com.skiwi.tcg.utils;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class ArgumentsTest {
    static {
        assertTrue(true);
    }

    /** Arguments.requirePositive **/

    @Test
    public void testRequirePositive_int() {
        int result = Arguments.requirePositive(1);
        assertEquals(1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirePositive_intIAE1() {
        Arguments.requirePositive(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirePositive_intIAE2() {
        Arguments.requirePositive(-1);
    }

    @Test
    public void testRequirePositive_int_String() {
        int result = Arguments.requirePositive(1, "test");
        assertEquals(1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirePositive_int_StringIAE1() {
        Arguments.requirePositive(0, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirePositive_int_StringIAE2() {
        Arguments.requirePositive(-1, "test");
    }

    @Test(expected = NullPointerException.class)
    public void testRequirePositive_int_StringNPE() {
        Arguments.requirePositive(1, null);
    }

    /** Arguments.requireNegative **/

    @Test
    public void testRequireNegative_int() {
        int result = Arguments.requireNegative(-1);
        assertEquals(-1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireNegative_intIAE1() {
        Arguments.requireNegative(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireNegative_intIAE2() {
        Arguments.requireNegative(1);
    }

    @Test
    public void testRequireNegative_int_String() {
        int result = Arguments.requireNegative(-1, "test");
        assertEquals(-1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireNegative_int_StringIAE1() {
        Arguments.requireNegative(0, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireNegative_int_StringIAE2() {
        Arguments.requireNegative(1, "test");
    }

    @Test(expected = NullPointerException.class)
    public void testRequireNegative_int_StringNPE() {
        Arguments.requireNegative(-1, null);
    }

    /** Arguments.requirePositiveOrZero **/

    @Test
    public void testRequirePositiveOrZero_int1() {
        int result = Arguments.requirePositiveOrZero(0);
        assertEquals(0, result);
    }

    @Test
    public void testRequirePositiveOrZero_int2() {
        int result = Arguments.requirePositiveOrZero(1);
        assertEquals(1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirePositiveOrZero_intIAE() {
        Arguments.requirePositiveOrZero(-1);
    }

    @Test
    public void testRequirePositiveOrZero_int_String1() {
        int result = Arguments.requirePositiveOrZero(0, "test");
        assertEquals(0, result);
    }

    @Test
    public void testRequirePositiveOrZero_int_String2() {
        int result = Arguments.requirePositiveOrZero(1, "test");
        assertEquals(1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirePositiveOrZero_int_StringIAE() {
        Arguments.requirePositiveOrZero(-1, "test");
    }

    @Test(expected = NullPointerException.class)
    public void testRequirePositiveOrZero_int_StringNPE() {
        Arguments.requirePositiveOrZero(0, null);
    }

    /** Arguments.requireNegativeOrZero **/

    @Test
    public void testRequireNegativeOrZero_int1() {
        int result = Arguments.requireNegativeOrZero(0);
        assertEquals(0, result);
    }

    @Test
    public void testRequireNegativeOrZero_int2() {
        int result = Arguments.requireNegativeOrZero(-1);
        assertEquals(-1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireNegativeOrZero_intIAE() {
        Arguments.requireNegativeOrZero(1);
    }

    @Test
    public void testRequireNegativeOrZero_int_String1() {
        int result = Arguments.requireNegativeOrZero(0, "test");
        assertEquals(0, result);
    }

    @Test
    public void testRequireNegativeOrZero_int_String2() {
        int result = Arguments.requireNegativeOrZero(-1, "test");
        assertEquals(-1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireNegativeOrZero_int_StringIAE() {
        Arguments.requireNegativeOrZero(1, "test");
    }

    @Test(expected = NullPointerException.class)
    public void testRequireNegativeOrZero_int_StringNPE() {
        Arguments.requireNegativeOrZero(0, null);
    }

    /** Arguments.requireInRange **/

    @Test
    public void testRequireInRange_3args() {
        int result = Arguments.requireInRange(1, 1, 2);
        assertEquals(1, result);
        int result2 = Arguments.requireInRange(1, 0, 3);
        assertEquals(1, result2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_3argsIAE1() {
        Arguments.requireInRange(1, 5, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_3argsIAE2() {
        Arguments.requireInRange(1, 6, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_3argsIAE3() {
        Arguments.requireInRange(1, 0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_3argsIAE4() {
        Arguments.requireInRange(1, 2, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_3argsIAE5() {
        Arguments.requireInRange(1, 10, 20);
    }

    @Test
    public void testRequireInRange_4args() {
        int result = Arguments.requireInRange(1, 1, 2, "test");
        assertEquals(1, result);
        int result2 = Arguments.requireInRange(1, 0, 3, "test");
        assertEquals(1, result2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_4argsIAE1() {
        Arguments.requireInRange(1, 5, 5, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_4argsIAE2() {
        Arguments.requireInRange(1, 6, 5, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_4argsIAE3() {
        Arguments.requireInRange(1, 0, 1, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_4argsIAE4() {
        Arguments.requireInRange(1, 2, 5, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRange_4argsIAE5() {
        Arguments.requireInRange(1, 10, 20, "test");
    }

    @Test(expected = NullPointerException.class)
    public void testRequireInRange_4argsNPE() {
        Arguments.requireInRange(1, 0, 2, null);
    }

    /** Arguments.requireInRangeClosed **/

    @Test
    public void testRequireInRangeClosed_3args() {
        int result = Arguments.requireInRangeClosed(1, 1, 2);
        assertEquals(1, result);
        int result2 = Arguments.requireInRangeClosed(1, 0, 1);
        assertEquals(1, result2);
        int result3 = Arguments.requireInRangeClosed(1, 1, 1);
        assertEquals(1, result3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRangeClosed_3argsIAE1() {
        Arguments.requireInRangeClosed(1, 6, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRangeClosed_3argsIAE2() {
        Arguments.requireInRangeClosed(1, -4, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRangeClosed_3argsIAE3() {
        Arguments.requireInRangeClosed(1, 2, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRangeClosed_3argsIAE4() {
        Arguments.requireInRangeClosed(1, 20, 40);
    }

    @Test
    public void testRequireInRangeClosed_4args() {
        int result = Arguments.requireInRangeClosed(1, 1, 2, "test");
        assertEquals(1, result);
        int result2 = Arguments.requireInRangeClosed(1, 0, 1, "test");
        assertEquals(1, result2);
        int result3 = Arguments.requireInRangeClosed(1, 1, 1, "test");
        assertEquals(1, result3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRangeClosed_4argsIAE1() {
        Arguments.requireInRangeClosed(1, 6, 5, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRangeClosed_4argsIAE2() {
        Arguments.requireInRangeClosed(1, -4, 0, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRangeClosed_4argsIAE3() {
        Arguments.requireInRangeClosed(1, 2, 5, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequireInRangeClosed_4argsIAE4() {
        Arguments.requireInRangeClosed(1, 20, 40, "test");
    }

    @Test(expected = NullPointerException.class)
    public void testRequireInRangeClosed_4argsNPE() {
        Arguments.requireInRangeClosed(5, 2, 20, null);
    }

    /** Arguments.requireIndexInRange **/

    @Test
    public void testRequireIndexInRange() {
        int result = Arguments.requireIndexInRange(1, 1, 2);
        assertEquals(1, result);
        int result2 = Arguments.requireIndexInRange(1, 0, 3);
        assertEquals(1, result2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRequireIndexInRangeIOOBE1() {
        Arguments.requireIndexInRange(1, 0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRequireIndexInRangeIOOBE2() {
        Arguments.requireIndexInRange(1, 2, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRequireIndexInRangeIOOBE3() {
        Arguments.requireIndexInRange(1, 10, 20);
    }

    /** Arguments.requireIndexInRangeClosed **/

    @Test
    public void testRequireIndexInRangeClosed() {
        int result = Arguments.requireIndexInRangeClosed(1, 1, 2);
        assertEquals(1, result);
        int result2 = Arguments.requireIndexInRangeClosed(1, 0, 1);
        assertEquals(1, result2);
        int result3 = Arguments.requireIndexInRangeClosed(1, 1, 1);
        assertEquals(1, result3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRequireIndexInRangeClosedIOOBE1() {
        Arguments.requireIndexInRangeClosed(1, -4, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRequireIndexInRangeClosedIOOBE2() {
        Arguments.requireIndexInRangeClosed(1, 2, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRequireIndexInRangeClosedIOOBE3() {
        Arguments.requireIndexInRangeClosed(1, 20, 40);
    }
    
    /** Arguments.requireMinimalLength **/
    
    @Test
    public void testRequireMinimalLength_3args() {
        assertEquals("test", Arguments.requireMinimalLength("test", 4));
    }
    
    @Test(expected = NullPointerException.class)
    public void testRequireMinimalLength_3argsNPE() {
        Arguments.requireMinimalLength(null, 4);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRequireMinimalLength_3argsIAE1() {
        Arguments.requireMinimalLength("test", 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRequireMinimalLength_3argsIAE2() {
        Arguments.requireMinimalLength("test", -1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRequireMinimalLength_3argsIAE3() {
        Arguments.requireMinimalLength("test", 5);
    }
    
    @Test
    public void testRequireMinimalLength_4args() {
        assertEquals("test", Arguments.requireMinimalLength("test", 4, "test"));
    }
    
    @Test(expected = NullPointerException.class)
    public void testRequireMinimalLength_4argsNPE1() {
        Arguments.requireMinimalLength(null, 4, "test");
    }
    
    @Test(expected = NullPointerException.class)
    public void testRequireMinimalLength_4argsNPE2() {
        Arguments.requireMinimalLength("test", 4, null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRequireMinimalLength_4argsIAE1() {
        Arguments.requireMinimalLength("test", 0, "test");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRequireMinimalLength_4argsIAE2() {
        Arguments.requireMinimalLength("test", -1, "test");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRequireMinimalLength_4argsIAE3() {
        Arguments.requireMinimalLength("test", 5, "test");
    }
}