/* *****************************************************************************
 *  Name: John Otto
 *  Date: 1/2/2023
 * Description: A double-ended queue or deque (pronounced “deck”) is a
 * generalization of a stack and a queue that supports adding and removing
 * items from either the front or the back of the data structure. Create a
 * generic data type Deque that implements the following API:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

// Time: O(1) for each deque operation
// Space: 48n + 192 bytes max
public class Deque<Item> implements Iterable<Item> {
    // first node
    private Node<Item> first;
    // last node
    private Node<Item> last;
    // size of the deque
    private int size;


    // best implemented with a doubly linked list
    private class Node<Item> {
        // a doubly linked list has stores some data and pointers to the
        // previous/next node
        Item item;
        Node<Item> prev;
        Node<Item> next;

        public Node(Item item) {
            this.item = item;
        }
    }

    // construct an empty deque
    public Deque() {
        first = new Node<>(null);
        last = new Node<>(null);
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        // check for illegal null inputs
        if (item == null) {
            throw new IllegalArgumentException("Null values cannot be added");
        }

        // create a new node with the item data
        Node<Item> newNode = new Node<>(item);

        // check if the linked list empty and make the first and last node
        // point to the newNode
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        }
        else {
            // make the new node the first node
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        // increment the current size
        size++;
    }

    // insert the item at the tail of the sequence
    public void addLast(Item item) {
        // check for illegal null inputs
        if (item == null) {
            throw new IllegalArgumentException("Null values cannot be added");
        }

        // create a new node with the item data
        Node<Item> newNode = new Node<>(item);

        // check for special case when first null and make first the new node
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        }
        else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        // increment the current size
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        else {
            first.prev = null;
        }
        return item;

    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Item item = last.item;
        last = last.prev;
        size--;
        if (isEmpty()) {
            first = null;
        }
        else {
            last.next = null;
        }
        return item;
    }


    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> curr;

        public DequeIterator() {
            curr = first;
        }

        public boolean hasNext() {
            return curr != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item data = curr.item;
            curr = curr.next;
            return data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deck = new Deque<>();
        deck.addFirst(1);
        deck.addFirst(2);
        deck.addFirst(3);
        deck.addFirst(4);
        deck.addFirst(5);
        System.out.println(deck.isEmpty()); // returns false
        System.out.println(deck.size()); // returns 5
        System.out.println(deck.removeFirst()); // returns 1
        System.out.println(deck.removeLast()); // return 5
        for (int item : deck) {
            System.out.println(item + " ");
        }
    }
}
