/**
 *
 */
package com.dxc.medxc.commons.store.implementation.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import com.dxc.medxc.commons.store.implementation.Register;
import com.dxc.medxc.commons.store.implementation.StoreImpl;

/**
 * @author mhristov2
 */
class StoreImplTest {

    /**
     *
     */
    @Test
    public void findExistingClassesShouldReturnNonNullTest() {
        final String testValueConstructor = "1";
        final Register<String> testRegister = new Register<>();
        final StoreImpl<String> testStorage = new StoreImpl<>(testValueConstructor, testRegister);
        testRegister.register(String.class, String::new);
        testRegister.register(StringBuilder.class, StringBuilder::new);
        testRegister.register(BigInteger.class, BigInteger::new);

        final StringBuilder testStringBuilder = new StringBuilder(testValueConstructor);
        assertEquals(testValueConstructor, testStorage.find(String.class));
        assertEquals(BigInteger.ONE, testStorage.find(BigInteger.class));
        assertEquals(testStringBuilder.toString(), testStorage.find(StringBuilder.class).toString());
    }

    /**
     *
     */
    @Test
    public void findNonExistingClassesShouldReturnNullTest() {
        final String testValueConstructor = "1";
        final Register<String> testRegister = new Register<>();
        final StoreImpl<String> testStorage = new StoreImpl<>(testValueConstructor, testRegister);
        assertNull(testStorage.find(String.class));
        assertNull(testStorage.find(BigInteger.class));
        assertNull(testStorage.find(StringBuilder.class));
    }

}
