package com.dxc.medxc.persistence.transaction;

import java.util.function.Function;

import com.dxc.medxc.commons.store.model.Store;

/**
 * Interface for the TransactionManagerImpl.
 *
 * @author atsekov
 */
public interface TransactionManager {

    /**
     * Execute around method for the transactions
     *
     * @param function
     *            the lambda function given
     * @param <R>
     *            result
     * @param action
     *            Lambda expression with the code that needs to be executed in the
     *            transaction.
     * @return result from the transaction
     */
    <R> R execute(final Function<Store, R> function);
}
