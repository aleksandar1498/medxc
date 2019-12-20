/**
 *
 */
package com.dxc.medxc.commons.store.implementation;

import java.util.function.Function;

import com.dxc.medxc.commons.store.model.Store;

/**
 * The purpose of this class is to store interfaces and their one-argument
 * constructor that have argument of type A while keeping the specified
 * constructor argument
 *
 * @author mhristov2
 * @param <A>
 *            The type of the argument that is passed to the stored constructors
 */
public final class StoreImpl<A> implements Store {
    private final A constructorArgument;
    private final Register<A> register;

    /**
     * @param constructorArgument
     *            The argument that is passed to the stored constructors
     * @param register
     *            The storage where the interfaces and their implementations are
     *            kept
     */
    public StoreImpl(final A constructorArgument, final Register<A> register) {
        this.register = register;
        this.constructorArgument = constructorArgument;
    }

    /**
     * @param <S>
     *            The type of the interface that is to be stored
     * @param serviceInterface
     *            The interface whose implementation is searched.
     * @return Return an object of type S created by the one argument constructor if
     *         the constructor associated with the interface exists, or null
     *         otherwise.
     */
    @Override
    public <S> S find(final Class<S> serviceInterface) {
        final Function<A, ?> constructor = register.find(serviceInterface);
        if (constructor != null) {
            return (S) constructor.apply(constructorArgument);
        }
        return null;
    }
}
