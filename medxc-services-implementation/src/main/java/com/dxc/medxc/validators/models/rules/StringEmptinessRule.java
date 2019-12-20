package com.dxc.medxc.validators.models.rules;

import com.dxc.medxc.validators.errors.ErrorType;

/**
 * @author astefanov2
 */
public class StringEmptinessRule implements EmptinessRule<String> {
    @Override
    public boolean validate(final String arg) {
        return !arg.isEmpty();
    }

    @Override
    public ErrorType getError() {
        return ErrorType.EMPTY;
    }

    @Override
    public Object[] getParams() {
        return new Object[0];
    }
}
