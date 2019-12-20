package com.dxc.medxc.validators.models.rules;

import java.util.regex.Pattern;

import com.dxc.medxc.validators.errors.ErrorType;

/**
 * @author astefanov2
 */
public class StringFormatRule implements FormatRule<String> {
    private final String regex;

    /**
     * @param regExp
     *            is the regular expression used for the validated String
     */
    public StringFormatRule(final String regExp) {
        regex = regExp;
    }

    @Override
    public boolean validate(final String arg) {
        final Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(arg).matches();
    }

    @Override
    public ErrorType getError() {
        return ErrorType.FORMAT;
    }

    @Override
    public Object[] getParams() {
        return new Object[] { regex };
    }
}
