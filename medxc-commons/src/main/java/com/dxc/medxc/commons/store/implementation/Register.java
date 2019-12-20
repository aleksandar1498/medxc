/**
 *
 */
package com.dxc.medxc.commons.store.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * The purpose of this class is to store interfaces and their one-argument
 * constructor that have argument of type A without keeping the specified
 * constructor argument.
 * 
 * @author mhristov2
 * @param <A>
 *            The type of the argument that is passed to the stored constructors
 */
public class Register<A> {
    private final Map<Class<?>, Function<A, ?>> storage;

    /**
     * Initialize storage
     */
    public Register() {
        storage = new HashMap<>();
    }

    /**
     * @param <S>
     *            The type of the interface that is to be stored
     * @param serviceInterface
     *            The interface that is stored
     * @param constructor
     *            The constructor of the implementation that is stored
     */
    public <S> void register(final Class<S> serviceInterface, final Function<A, S> constructor) {
        storage.put(serviceInterface, constructor);
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
    public <S> Function<A, ?> find(final Class<S> serviceInterface) {
        return storage.get(serviceInterface);
    }
}
