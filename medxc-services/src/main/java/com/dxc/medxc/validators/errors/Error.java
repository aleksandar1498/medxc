package com.dxc.medxc.validators.errors;

import java.util.Arrays;

/**
 * @author astefanov2
 */
public class Error {

    @SuppressWarnings("nls")
    private static final String ERROR_OUTPUT_FORMAT = "%s %s";
    @SuppressWarnings("nls")
    private static final String EMPTY_STRING = "";

    private ErrorType errorKey;
    private Object[] params;

    /**
     * @param type
     *            is the ErrorType of the failed validation
     * @param errorParams
     *            are the parameters that were used for the validation
     */
    public Error(final ErrorType type, final Object... errorParams) {
        params = errorParams.clone();
        errorKey = type;
    }

    /**
     * @return the parameters
     */
    public Object[] getParams() {
        return params.clone();
    }

    /**
     * @param errorParams
     *            are the parameters that were used for the validation
     */
    public void setParams(final Object... errorParams) {
        params = errorParams.clone();
    }

    /**
     * @return the type of the error
     */
    public ErrorType getErrorKey() {
        return errorKey;
    }

    /**
     * @param type
     *            is the type of the error occurred
     */
    public void setErrorKey(final ErrorType type) {
        this.errorKey = type;
    }

    @Override
    public String toString() {
        return String.format(ERROR_OUTPUT_FORMAT, errorKey,
                params.length > 0 ? Arrays.asList(params).toString() : EMPTY_STRING);
    }
}
