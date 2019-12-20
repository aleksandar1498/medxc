package com.dxc.medxc.validators.exceptions;

import java.util.List;
import java.util.Map;

import com.dxc.medxc.validators.errors.Error;

/**
 * @author astefanov2
 */
public class ValidationException extends RuntimeException {
    /**
     * is the generated code needed for the serialization
     */
    private static final long serialVersionUID = -4631050733555991579L;

    private final Map<String, List<Error>> rootErrors;

    /**
     * @param errors
     *            are the series of error occurred over the validation
     */
    public ValidationException(final Map<String, List<Error>> errors) {
        super();
        rootErrors = errors;
    }

    /**
     * @return the series of error occurred over the validation
     */
    public Map<String, List<Error>> getRootErrors() {
        return rootErrors;
    }
}
