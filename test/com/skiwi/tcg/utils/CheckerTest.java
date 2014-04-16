
package com.skiwi.tcg.utils;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Beheerder
 */
public class CheckerTest {
    static {
        assertTrue(true);
    }
    
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testThrowOnFail_boolean_Supplier1() {
        Checker.throwOnFail(true, CustomException::new);
    }

    @Test(expected = CustomException.class)
    public void testThrowOnFail_boolean_Supplier2() {
        Checker.throwOnFail(false, CustomException::new);
    }

    @Test(expected = NullPointerException.class)
    public void testThrowOnFail_boolean_SupplierNPE() {
        Checker.throwOnFail(true, null);
    }

    @Test
    public void testThrowOnFail_3args1() {
        Checker.throwOnFail(true, CustomException::new, "custom");
    }
    
    @Test
    public void testThrowOnFail_3args2() {
        exception.expect(CustomException.class);
        exception.expectMessage("custom");
        Checker.throwOnFail(false, CustomException::new, "custom");
    }
    
    @Test(expected = NullPointerException.class)
    public void testThrowOnFail_3argsNPE1() {
        Checker.throwOnFail(true, null, "custom");
    }
    
    @Test(expected = NullPointerException.class)
    public void testThrowOnFail_3argsNPE2() {
        Checker.throwOnFail(true, CustomException::new, null);
    }

    @Test
    public void testThrowOnSuccess_boolean_Supplier1() {
        Checker.throwOnSuccess(false, CustomException::new);
    }

    @Test(expected = CustomException.class)
    public void testThrowOnSuccess_boolean_Supplier2() {
        Checker.throwOnSuccess(true, CustomException::new);
    }

    @Test(expected = NullPointerException.class)
    public void testThrowOnSuccess_boolean_SupplierNPE() {
        Checker.throwOnSuccess(false, null);
    }

    @Test
    public void testThrowOnSuccess_3args1() {
        Checker.throwOnSuccess(false, CustomException::new, "custom");
    }
    
    @Test
    public void testThrowOnSuccess_3args2() {
        exception.expect(CustomException.class);
        exception.expectMessage("custom");
        Checker.throwOnSuccess(true, CustomException::new, "custom");
    }
    
    @Test(expected = NullPointerException.class)
    public void testThrowOnSuccess_3argsNPE1() {
        Checker.throwOnSuccess(false, null, "custom");
    }
    
    @Test(expected = NullPointerException.class)
    public void testThrowOnSuccess_3argsNPE2() {
        Checker.throwOnSuccess(false, CustomException::new, null);
    }

    @Test
    public void testCheckArgument_boolean1() {
        Checker.checkArgument(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckArgument_boolean2() {
        Checker.checkArgument(false);
    }

    @Test
    public void testCheckArgument_boolean_String1() {
        Checker.checkArgument(true, "custom");
    }

    @Test
    public void testCheckArgument_boolean_String2() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("custom");
        Checker.checkArgument(false, "custom");
    }
    
    @Test(expected = NullPointerException.class)
    public void testCheckArgument_boolean_StringNPE() {
        Checker.checkArgument(true, null);
    }

    @Test
    public void testCheckState_boolean1() {
        Checker.checkState(true);
    }

    @Test(expected = IllegalStateException.class)
    public void testCheckState_boolean2() {
        Checker.checkState(false);
    }

    @Test
    public void testCheckState_boolean_String1() {
        Checker.checkState(true, "custom");
    }

    @Test
    public void testCheckState_boolean_String2() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("custom");
        Checker.checkState(false, "custom");
    }
    
    @Test(expected = NullPointerException.class)
    public void testCheckState_boolean_StringNPE() {
        Checker.checkState(true, null);
    }

    @Test
    public void testCheckIndex_boolean1() {
        Checker.checkIndex(true);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCheckIndex_boolean2() {
        Checker.checkIndex(false);
    }

    @Test
    public void testCheckIndex_boolean_String1() {
        Checker.checkIndex(true, "custom");
    }

    @Test
    public void testCheckIndex_boolean_String2() {
        exception.expect(IndexOutOfBoundsException.class);
        exception.expectMessage("custom");
        Checker.checkIndex(false, "custom");
    }
    
    @Test(expected = NullPointerException.class)
    public void testCheckIndex_boolean_StringNPE() {
        Checker.checkIndex(true, null);
    }
    
    private static class CustomException extends RuntimeException {
        public CustomException() { }
        
        public CustomException(final String message) {
            super(message);
        }
    }
}
