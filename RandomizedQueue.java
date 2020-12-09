import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;

    private class RandomizedQueueIterator implements Iterator<Item> {
        private RandomizedQueue<Item> queue = new RandomizedQueue<Item>();

        public RandomizedQueueIterator() {
            for (int i = 0; i < size; i++) {
                queue.enqueue(items[i]);
            }
        }

        public boolean hasNext() {
            return queue.size() > 0;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("The iterator is empty!");
            return queue.dequeue();
        }

        public void remove() {
            throw new UnsupportedOperationException("remove() is not supported.");
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1]; // ugly
    }

    // // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null!");
        if (size == items.length) resize(2 * items.length);
        items[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException("The queue is empty!");

        int index = StdRandom.uniform(size);
        Item item = items[index];
        items[index] = items[--size];

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) throw new NoSuchElementException("The queue is empty!");
        int index = StdRandom.uniform(size);
        return items[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E");
        queue.enqueue("F");
        queue.enqueue("G");

        Iterator<String> firstIterator = queue.iterator();

        StdOut.print("First Iterator Result: ");

        while (firstIterator.hasNext()) {
            StdOut.print(firstIterator.next());
        }

        Iterator<String> secondIterator = queue.iterator();

        StdOut.print("\nSecond Iterator Result: ");

        while (secondIterator.hasNext()) {
            StdOut.print(secondIterator.next());
        }
    }

    // resize helper
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity]; // ugly

        for (int i = 0; i < size; i++) {
            copy[i] = items[i];
        }

        items = copy;
    }
}