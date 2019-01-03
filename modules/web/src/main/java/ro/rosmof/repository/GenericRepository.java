package ro.rosmof.repository;

import java.util.List;

public interface GenericRepository<K, T> {
    /**
     * Returns the Id for an item
     */
    K getId(T value) throws Exception;


    /**
     * Returns all the items for this repository
     */
    List<T> getAllElements() throws Exception;

    /**
     * Returns a single item by id
     */
    T getElementById(K id) throws Exception;

    /**
     * Saves an element and returns the id
     */
    K save(T element) throws Exception;
}
