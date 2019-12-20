package com.dxc.medxc.validators.errors;

/**
 * @author astefanov2
 */
public enum ErrorType {
    /**
     * This type of error occurs in situation in which the validated object is empty
     */
    @SuppressWarnings("nls")
    EMPTY("is empty"),
    /**
     * This type of error occurs in situation in which the validated object is not
     * in the predefined range
     */
    @SuppressWarnings("nls")
    NOT_IN_RANGE("is not in the needed range"),
    /**
     * This type of error occurs in situation in which the validated object is null
     */
    @SuppressWarnings("nls")
    NULL("is empty"),
    /**
     * This type of error occurs in situation in which the validated object should
     * be before another one
     */
    @SuppressWarnings("nls")
    BEFORE("should be before"),
    /**
     * This type of error occurs in situation in which the validated object should
     * be after another one
     */
    @SuppressWarnings("nls")
    AFTER("is after"),
    /**
     * This type of error occurs in situation in which the validated object should
     * be before another one
     */
    @SuppressWarnings("nls")
    IS_MORE("is more than the predefined limit"),
    /**
     * This type of error occurs in situation in which the validated object should
     * be more(value) than another one
     */
    @SuppressWarnings("nls")
    IS_LESS("is less than the predefined limit"),
    /**
     * This type of error occurs in situation in which the validated object should
     * be less(value) than another one
     */
    @SuppressWarnings("nls")
    FORMAT("is not in the format predefined");

    private String message;

    ErrorType(final String errorMessage) {
        this.message = errorMessage;
    }

    /**
     * @return the message of this error type
     */
    public String getMessage() {
        return message;
    }
}
