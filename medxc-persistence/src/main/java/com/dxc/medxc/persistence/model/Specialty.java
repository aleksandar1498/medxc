/**
 *
 */
package com.dxc.medxc.persistence.model;

import java.util.List;

/**
 * @author yyayya
 */
public interface Specialty {
    /**
     * \
     *
     * @return Id of the specialty
     */
    int getID();

    /**
     * @return Name of the specialty
     */
    String getName();

    /**
     * @param doctors
     *            the specialties of the doctor
     */
    void setDocSpec(final List<DocSpec> doctors);
}
