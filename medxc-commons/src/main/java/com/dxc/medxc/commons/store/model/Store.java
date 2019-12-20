/**
 *
 */
package com.dxc.medxc.commons.store.model;

/**
 * The purpose of this class is to store the interface and one of its
 * implementations' constructor
 * 
 * @author mhristov2
 */
public interface Store {

    /**
     * @param <S>
     *            The type of the interface that is to be found
     * @param serviceInterface
     *            The interface that is searched by
     * @return Return an instance of the implementation of the searched interface,
     *         that is stored
     */
    <S> S find(final Class<S> serviceInterface);
}
