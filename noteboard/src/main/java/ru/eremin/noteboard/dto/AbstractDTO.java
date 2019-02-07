package ru.eremin.noteboard.dto;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

public abstract class AbstractDTO {

    public abstract boolean equals(Object o);

    public abstract int hashCode();

    public abstract String toString();

}
