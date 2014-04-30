
package com.skiwi.tcg.view.interfaces;

/**
 * An interface for objects that are viewable via a view.
 * 
 * @author Frank van Heeswijk
 * @param <T>   The type of viewable object
 * @param <V>   The concrete view on the viewable object
 */
public interface Viewable<T extends Viewable<T, V>, V extends View<T>> {
    public void addViewCallback(final V view);

    public void removeViewCallback(final V view);
}
