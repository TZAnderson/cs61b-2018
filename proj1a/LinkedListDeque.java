public class LinkedListDeque<T>  {
    private Node firstSentinel;
    private Node lastSentinel;
    private int size;

    private class Node {
        private Node prev;
        private T item;
        private Node next;

        private Node(Node prev, T item, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    public LinkedListDeque() {
        firstSentinel = new Node(null, null, null);
        lastSentinel = new Node(firstSentinel, null, null);
        firstSentinel.next = lastSentinel;
        size = 0;
    }

    /*
    [ before ]
    firstSentinel <-> lastSentinel
    [ after ]
    firstSentinel <-> newNode(item1) <-> lastSentinel
    */
    public void addFirst(T item) {
        Node newNode = new Node(firstSentinel, item, firstSentinel.next);
        firstSentinel.next.prev = newNode;
        firstSentinel.next = newNode;
        size += 1;
    }
    /*
    [ before ]
    firstSentinel <-> newNode(item1) <-> lastSentinel
    [ after ]
    firstSentinel <-> newNode(item1) <-> newNode(item2) <-> lastSentinel
    */
    public void addLast(T item) {
        Node newNode = new Node(lastSentinel.prev, item, lastSentinel);
        lastSentinel.prev.next = newNode;
        lastSentinel.prev = newNode;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        Node current = firstSentinel.next;
        while (current != lastSentinel) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node first = firstSentinel.next;
        firstSentinel.next = first.next;
        first.next.prev = firstSentinel;
        size -= 1;
        return first.item;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node last = lastSentinel.prev;
        lastSentinel.prev = last.prev;
        last.prev.next = lastSentinel;
        size -= 1;
        return last.item;
    }
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node current = firstSentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }
    public T getRecursive(int index) {
        return getRecursiveHelper(index, firstSentinel.next);
    }
    private T getRecursiveHelper(int index, Node current) {
        if (index < 0 || current == lastSentinel) {
            return null;
        }
        if (index == 0) {
            return current.item;
        }
        return getRecursiveHelper(index - 1, current.next);
    }
}
