/**
 *
 */
package com.dxc.medxc.dto;

/**
 * @author amirchev
 */
public final class TestTypeDTO {
    private final String testTypeName;

    /// type to be eventually changed
    private final String refValues;

    /**
     * Constructor of TetsTypeDTO
     * 
     * @param testTypeName
     * @param refValues
     */
    public TestTypeDTO(final String testTypeName, final String refValues) {
        this.testTypeName = testTypeName;
        this.refValues = refValues;
    }

    /**
     * @return the testTypeName
     */
    public String getTestTypeName() {
        return testTypeName;
    }

    /**
     * @return the refValues
     */
    public String getRefValues() {
        return refValues;
    }
}
