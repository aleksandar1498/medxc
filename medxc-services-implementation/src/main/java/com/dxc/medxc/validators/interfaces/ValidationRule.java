package com.dxc.medxc.validators.interfaces;

import com.dxc.medxc.validators.errors.ErrorType;

/**
 * @author astefanov2
 * @param <T>
 */
public interface ValidationRule<T> {
    /**
     * @param arg
     *            is the validated argument
     * @return true if the validation is successful ,in the opposite case it returns
     *         false
     */
    boolean validate(T arg);

    /**
     * @return the error type that the Validation may generate
     */
    ErrorType getError();

    /**
     * @return the parameters used for the validation
     */
    Object[] getParams();
}
