
public class MyList<T> implements ListInterface<T>{


    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    /**
     * Default constructor
     */
    public MyList() {
    }

    /**
     * Determine whether list is empty
     *
     * @return true if list is empty
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    /**
     * Inserts the data at the front of the list
     *
     * @param data the inserted data
     */
    @Override
    public void insertAtFront(T data) {
        Node<T> n = new Node<>(data);
        size++;

        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            n.setNext(head);
            head = n;
        }
    }

    /**
     * Inserts the data at the end of the list
     *
     * @param data the inserted item
     */
    @Override
    public void insertAtBack(T data) {
        Node<T> n = new Node<>(data);
        size++;

        if (isEmpty()) {
            head = n;
            tail = n;

        } else {
            tail.setNext(n);           
            tail = n;

        }
    }

    public T remove(T data) throws Exception{

        if (head == null) return null;
        
        Node<T> cur = head;
        if (cur.getData() == data) return removeFromFront();

        while (cur.getNext() != null) {
            if (cur.getNext().getData() == data) {break;}
            cur = cur.getNext();
        }

        if (cur == tail && cur.getData() == data) {
            return removeFromBack();
        }

        if (cur != null) {
            T toberemoved = cur.getData();            
            size--;
            cur.setNext(cur.getNext().getNext());
            return toberemoved;
        }
        System.out.println("Not removed succesfully");
        return null;
    }

    public boolean isInside(T data) {
        if (head == null) return false;
        Node<T> cur = head;

        while (cur.getNext() != null) {
            if (cur.getData() == data) break;
            cur = cur.getNext();
        }
        
        return data == cur.getData();
    }

    /**
     * Returns and removes the data from the list head
     *
     * @return the data contained in the list head
     * @throws EmptyListException if the list is empty
     */
    @Override
    public T removeFromFront() throws Exception {
        if (isEmpty())
            throw new Exception();

        T data = head.getData();
        size--;

        if (head == tail)
            head = tail = null;
        else
            head = head.getNext();

        return data;
    }

    /**
     * Returns and removes the data from the list tail
     *
     * @return the data contained in the list tail
     * @throws EmptyListException if the list is empty
     */
    @Override
    public T removeFromBack() throws Exception {
        if (isEmpty())
            throw new Exception();

        T data = tail.getData();
        size--;

        if (head == tail)
            head = tail = null;
        else {
            Node<T> iterator = head;
            while (iterator.getNext() != tail)
                iterator = iterator.getNext();

            iterator.setNext(null);
            tail = iterator;
        }

        return data;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    /**
     * Returns list as String
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "List is empty";
        }

        Node<T> current = head;

        StringBuilder ret = new StringBuilder();
        int i = 1;
        // while not at end of list, output current node's data
        ret.append("\n");

        while (current != null) {
            ret.append(i+".");
            ret.append(current.data.toString());

            if (current.getNext() != null) {

                ret.append("\n");
            }
            current = current.next;
            i++;
        }
        return ret.toString();
    }
}

