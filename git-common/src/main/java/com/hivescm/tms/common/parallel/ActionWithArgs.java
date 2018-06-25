package com.hivescm.tms.common.parallel;

/**
 * Created by xiexindong on 2017-08-21.
 */
@FunctionalInterface
public interface ActionWithArgs {
    Object[] parameters = null;

    void doAction(Object[] args);
}
