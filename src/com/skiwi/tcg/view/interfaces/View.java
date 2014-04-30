
package com.skiwi.tcg.view.interfaces;

/**
 * A marker interface to denote that an object implements a view on some other object.
 *
 * @author Frank van Heeswijk
 * @param <T>   The type of object that is viewed
 */
public interface View<T extends Viewable<T, ? extends View<T>>> { }
