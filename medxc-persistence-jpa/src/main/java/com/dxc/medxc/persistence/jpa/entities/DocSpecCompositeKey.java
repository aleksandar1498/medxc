package com.dxc.medxc.persistence.jpa.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author lnikolaeva
 */
@Embeddable
public class DocSpecCompositeKey implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6837678206513937530L;

    @Column(name = "DOC_NUM", nullable = false, length = 10)
    private String docNum;

    @Column(name = "SPEC_ID", nullable = false, length = 4)
    private int specId;

    @Column(name = "ODD", length = 1)
    private int odd;

    /**
     * default constructor
     */
    public DocSpecCompositeKey() {

    }

    /**
     * @param docNum
     *            doctor's id number
     * @param specId
     *            specialty id
     * @param odd
     *            doctor's working days
     */
    public DocSpecCompositeKey(final String docNum, final int specId, final int odd) {
        this.docNum = docNum;
        this.specId = specId;
        this.odd = odd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(docNum, Integer.valueOf(odd), Integer.valueOf(specId));
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final DocSpecCompositeKey other = (DocSpecCompositeKey) obj;
        return Objects.equals(this.docNum, other.docNum) && this.odd == other.odd && this.specId == other.specId;
    }

    /**
     * @return the docNum
     */
    public String getDocNum() {
        return this.docNum;
    }

    /**
     * @return the specId
     */
    public int getSpecId() {
        return this.specId;
    }

    /**
     * @return the odd
     */
    public int getOdd() {
        return this.odd;
    }

}
