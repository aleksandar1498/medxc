package com.dxc.medxc.validators.models.rules;

import java.time.ZonedDateTime;

import com.dxc.medxc.validators.errors.ErrorType;

/**
 * @author astefanov2
 */
public class DateBeforeRule implements ConstraintsRule<ZonedDateTime> {
    private final ZonedDateTime dateToCompare;

    /**
     * @param currentDate
     *            is the date that will be used for the comparison
     */
    public DateBeforeRule(final ZonedDateTime currentDate) {
        this.dateToCompare = currentDate;
    }

    @Override
    public boolean validate(final ZonedDateTime date) {
        return date.isBefore(dateToCompare);
    }

    @Override
    public ErrorType getError() {
        return ErrorType.BEFORE;
    }

    @Override
    public Object[] getParams() {
        return new Object[] { dateToCompare };
    }

}
