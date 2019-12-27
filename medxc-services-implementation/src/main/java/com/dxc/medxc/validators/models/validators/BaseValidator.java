package com.dxc.medxc.validators.models.validators;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dxc.medxc.validators.errors.Error;
import com.dxc.medxc.validators.exceptions.ValidationException;
import com.dxc.medxc.validators.interfaces.ValidationRule;

/**
 * @author astefanov2
 */
public class BaseValidator {
    private final Map<String, List<Error>> errors;

    /**
     * initialization of the errors Map
     */
    public BaseValidator() {
        errors = new LinkedHashMap<>();
    }

    /**
     * @param identifier
     *            is used to identify uniquely the validated object
     * @param obj
     *            is the object that is validated
     * @param rule
     *            is the ValidationRule according to which the object is validated
     */
    public <T> void validate(final String identifier, final T obj, final ValidationRule<T> rule) {
        if (!rule.validate(obj)) {
            errors.putIfAbsent(identifier, new ArrayList<>());
            errors.get(identifier).add(new Error(rule.getError(), rule.getParams()));
        }
    }

    /**
     * @throws ValidationException
     *             if there are not successful validations
     */
    public void checkValidated() {
        if (this.errors.size() > 0) {
            throw new ValidationException(errors);
        }
    }

    /**
     * @return the errors present at that moment
     */
    public Map<String, List<Error>> getErrors() {
        return errors;
    }

}
