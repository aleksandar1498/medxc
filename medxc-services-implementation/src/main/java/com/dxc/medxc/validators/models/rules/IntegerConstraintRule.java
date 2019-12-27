package com.dxc.medxc.validators.models.rules;

import com.dxc.medxc.validators.errors.ErrorType;

public class IntegerConstraintRule implements ConstraintsRule<Integer> {
    private final  int lowLimit;
    private  final  int topLimit;

    public IntegerConstraintRule(int lowLimit, int topLimit) {
        this.lowLimit = lowLimit;
        this.topLimit = topLimit;
    }

    @Override
    public boolean validate(Integer arg) {
        return arg >= getLowLimit() && arg <= getTopLimit();
    }

    @Override
    public ErrorType getError() {
        return ErrorType.NOT_IN_RANGE;
    }

    @Override
    public Object[] getParams() {
        return new Object[]{this.lowLimit,this.topLimit};
    }

    public int getLowLimit() {
        return lowLimit;
    }

    public int getTopLimit() {
        return topLimit;
    }
}
