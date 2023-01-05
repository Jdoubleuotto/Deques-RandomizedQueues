/* *****************************************************************************
 *  Name: John Otto
 *  Date: 1/2/2023
 *  Description: A randomized queue is similar to a stack or queue, except that
 *  the item removed is chosen uniformly at random among items in the data
 *  structure. Create a generic data type RandomizedQueue that implements the
 *  following API:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // instance variables
    // total elements in the array that are not null
    private int size;
    // an array implementation to store the elements in the queue
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        // create an initial empty array of 2
        // we need to cast because generic array creation is not allowed
        queue = (Item[]) new Object[2];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        // check for illegal null inputs
        if (item == null) {
            throw new IllegalArgumentException("Null items cannot be added");
        }

        // if the array is at capacity then double it
        if (size == queue.length) {
            Item[] twice = (Item[]) new Object[2 * queue.length];
            for (int i = 0; i < size; i++) {
                twice[i] = queue[i];
            }
            queue = twice;
        }
        // add the item to queue while incrementing the size
        queue[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        // if the total elements is less than or equal to 1/4 of the array then resize it
        if (size > 0 && size <= queue.length / 4) {
            Item[] half = (Item[]) new Object[queue.length / 2];
            for (int i = 0; i < size; i++) {
                half[i] = queue[i];
            }
            queue = half;
        }

        // remove an element at random by assignment an element from the end
        // of the elements to a q[random] and make the end null
        int random = StdRandom.uniformInt(size);
        Item data = queue[random];
        queue[random] = queue[--size];
        queue[size] = null;

        // return data;
        return data;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Null items cannot be added");
        }
        int randomIndex = StdRandom.uniformInt(0, size);
        return queue[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {
        private Item[] items;
        private int index;

        public RandomQueueIterator() {
            items = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                items[i] = queue[i];
            }
            StdRandom.shuffle(items);
            index = 0;
        }

        public boolean hasNext() {
            return index < size;
        }

        public Item next() {
            if (!hasNext() || isEmpty()) {
                throw new NoSuchElementException();
            }
            Item data = items[index++];
            return data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    private int getQLen() {
        return queue.length;
    }

    private Item[] getQ() {
        return queue;
    }

    // unit testing (required)
    public static void main(String[] args) {
        // need to test every public method
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        // enqueue 5 items into the randomized queue
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);

        System.out.println(q.size());  // should print 5
        System.out.println(q.isEmpty());  // should print false
        System.out.println(q.sample());  // should print a random item from the queue
        System.out.println(q.dequeue());  // should print a random item from the queue
        System.out.println(q.size());  // should print 4
        for (int item : q) {
            System.out.print(item + " ");  // should print the items in random order
        }
    }
    // System.out.println(q.dequeue()); // should print a random item in the queue
    // System.out.println("size = " + q.size());
    // System.out.println("queue = " + q.getQLen());
    //
    // System.out.println(q.dequeue());
    // System.out.println("size = " + q.size());
    // System.out.println("queue = " + q.getQLen());
    //
    // System.out.println(q.dequeue());
    // System.out.println("size = " + q.size());
    // System.out.println("queue = " + q.getQLen());
    //
    // System.out.println(q.dequeue());
    // System.out.println("size = " + q.size());
    // System.out.println("queue = " + q.getQLen());
    //
    // System.out.println(q.dequeue());
    // System.out.println("size = " + q.size());
    // System.out.println("queue = " + q.getQLen());

}
