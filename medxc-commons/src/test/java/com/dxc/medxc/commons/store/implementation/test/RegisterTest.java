/**
 *
 */
package com.dxc.medxc.commons.store.implementation.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import com.dxc.medxc.commons.store.implementation.Register;

/**
 * @author mhristov2
 */
class RegisterTest {

    /**
     *
     */
    @Test
    public void findExistingClassesShouldReturnNonNullTest() {
        final Register<String> testRegister = new Register<>();
        testRegister.register(String.class, String::new);
        testRegister.register(StringBuilder.class, StringBuilder::new);

        assertNotNull(testRegister.find(String.class));
        assertNotNull(testRegister.find(StringBuilder.class));
    }

    /**
     *
     */
    @Test
    public void findNonExistingClassesShouldReturnNullTest() {
        final Register<String> testRegister = new Register<>();
        assertNull(testRegister.find(String.class));
        assertNull(testRegister.find(BigInteger.class));
        assertNull(testRegister.find(StringBuilder.class));
    }

}
