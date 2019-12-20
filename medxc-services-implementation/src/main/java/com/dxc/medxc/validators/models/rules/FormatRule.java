package com.dxc.medxc.validators.models.rules;

import com.dxc.medxc.validators.interfaces.ValidationRule;

/**
 * @author astefanov2
 * @param <T>
 *            is the type of object on which the format rule is applied
 */
public interface FormatRule<T> extends ValidationRule<T> {
    /**
     * Rule for checking if an object is in the defined format
     */
}
