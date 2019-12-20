package com.dxc.medxc.validators.models.rules;

import com.dxc.medxc.validators.errors.ErrorType;

/**
 * @author astefanov2
 */
public class StringConstraintRule implements ConstraintsRule<String> {
    private final int minLength;
    private final int maxLength;

    /**
     * @param lowLimit
     *            is the minimum length of the string (inclusive)
     * @param topLimit
     *            is the maximum length of the string (inclusive)
     */
    public StringConstraintRule(final int lowLimit, final int topLimit) {
        minLength = lowLimit;
        maxLength = topLimit;
    }

    @Override
    public boolean validate(final String arg) {
        return arg.length() >= minLength && arg.length() <= maxLength;
    }

    @Override
    public ErrorType getError() {
        return ErrorType.NOT_IN_RANGE;
    }

    @Override
    public Object[] getParams() {
        return new Object[] { Integer.valueOf(minLength), Integer.valueOf(maxLength) };
    }

    /**
     * @return the minimum length
     */
    public int getMinLength() {
        return minLength;
    }

    /**
     * @return the maximum length
     */
    public int getMaxLength() {
        return maxLength;
    }

}
