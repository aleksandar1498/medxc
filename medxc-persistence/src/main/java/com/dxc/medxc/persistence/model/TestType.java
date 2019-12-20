/**
 *
 */
package com.dxc.medxc.persistence.model;

import java.util.List;

/**
 * @author astefanov2
 */
public interface TestType {
    /**
     * @return the id of the test
     */
    int getId();

    /**
     * @return the name of the test
     */
    String getName();

    /**
     * @return the referent limits of the test
     */
    String getRefLimits();

    /**
     * @return a list of all the tests created for this type
     */
    List<Test> getTests();

}
