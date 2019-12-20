package com.dxc.medxc.validators.models.rules;

import com.dxc.medxc.validators.interfaces.ValidationRule;

/**
 * @author astefanov2
 * @param <T>
 *            is the type of object on which the rule is applied
 */
public interface ConstraintsRule<T> extends ValidationRule<T> {
    /**
     * Abstract class for limits such as IN_RANGE OUT_RANGE AFTER BEFORE
     */
}
