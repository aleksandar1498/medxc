/**
 *
 */
package com.dxc.medxc.converters;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utility class containing methods for for converting objects
 *
 * @author yyayya
 */
public final class ObjectConverter {

    private ObjectConverter() {
        // Utility class
    }

    /**
     * Generic method for mapping object to object in different type
     *
     * @param <S>
     *            object which we are converting
     * @param <T>
     *            object to which we are converting <S>
     * @param func
     *            Mapping function
     * @param obj
     *            Object of type S
     * @return <T> Converted object
     */

    public static <T, S> T convert(final Function<S, T> func, final S obj) {
        return func.apply(obj);
    }

    /**
     * Generic method for mapping list of objects to list of objects in different
     * type
     *
     * @param <S>
     *            object which we are converting
     * @param <T>
     *            object to which we are converting <S>
     * @param func
     *            Mapping function
     * @param list
     *            List of objects of type S
     * @return List<T> containing the converted elements from the list input
     */
    public static <T, S> List<T> convertList(final Function<S, T> func, final Collection<S> list) {
        return list.stream().map(func).collect(Collectors.toList());
    }

}
