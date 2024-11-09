

public interface ListInterface<T> {
    /**
     * Inserts the data at the front of the list
     *
     * @param data the inserted data
     */
    void insertAtFront(T data);

    int getSize();

    /**
     * Inserts the data at the end of the list
     *
     * @param data the inserted item
     */
    void insertAtBack(T data);

    /**
     * Returns and removes the data from the list head
     *
     * @return the data contained in the list head
     * @throws EmptyListException if the list is empty
     * @throws Exception 
     */
    T removeFromFront() throws Exception;

    /**
     * Returns and removes the data from the list tail
     *
     * @return the data contained in the list tail
     * @throws EmptyListException if the list is empty
     */
    T removeFromBack() throws Exception;

    /**
     * Determine whether list is empty
     *
     * @return true if list is empty
     */
    boolean isEmpty();
}
