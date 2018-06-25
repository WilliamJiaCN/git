package com.hivescm.tms.common.parallel;

/**
 * Created by xiexindong on 2017-08-21.
 */
@FunctionalInterface
public interface FuncWithArgs<T> {
    T doFunc(Object[] args);
}
