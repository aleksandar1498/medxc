/**
 * 
 */
package com.dxc.medxc.validators.models.rules;

/**
 * @author atsekov
 */
public class StringConstraintTopLimitRule extends StringConstraintRule {

    /**
     * @param topLimit
     */
    public StringConstraintTopLimitRule(int topLimit) {
        super(0, topLimit);
    }

    @Override
    public Object[] getParams() {
        return new Object[] { Integer.valueOf(super.getMaxLength()) };
    }

}
