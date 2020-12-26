package StacksAndQueues;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        public Item item;
        public Node next, previous;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException("The iterator is empty!");

            Item item = current.item;
            current = current.next;

            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove() is not supported.");
        }
    }

    private int counter;
    private Node head, tail;

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
        counter = 0;
    }

    // is the deque empty?
    public boolean isEmpty() { return counter == 0; }

    // return the number of items on the deque
    public int size() { return counter; }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null!");

        Node nextNode = head;

        head = new Node();
        head.item = item;
        head.next = nextNode;
        head.previous = null;

        if (isEmpty()) {
            tail = head;
        } else {
            nextNode.previous = head;
        }

        counter++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null!");

        Node previousNode = tail;

        tail = new Node();
        tail.item = item;
        tail.next = null;
        tail.previous = previousNode;

        if (isEmpty()) {
            head = tail;
        } else {
            previousNode.next = tail;
        }

        counter++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size() == 0) throw new NoSuchElementException("Deque is empty!");

        Item item = head.item;

        head = head.next;

        if (head == null) {
            tail = null;
        } else {
            head.previous = null;
        }

        counter--;

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size() == 0) throw new NoSuchElementException("Deque is empty!");

        Item item = tail.item;

        tail = tail.previous;

        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }

        counter--;

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();

        StdOut.println("The initial deque size: " + deque.size());

        deque.addFirst("A");
        deque.addFirst("C");
        deque.addFirst("B");
        deque.addFirst(deque.removeLast());

        StdOut.println("The current deque size: " + deque.size());

        Iterator<String> inorderIterator = deque.iterator();

        StdOut.print("In order: ");

        while (inorderIterator.hasNext()) {
            StdOut.print(inorderIterator.next());
        }

        deque.removeFirst();
        deque.addLast(deque.removeFirst());
        deque.addLast("A");

        Iterator<String> reverseOrderIterator = deque.iterator();

        StdOut.print("\nReverse order: ");

        while (reverseOrderIterator.hasNext()) {
            StdOut.print(reverseOrderIterator.next());
        }

        while (!deque.isEmpty()) {
            deque.removeFirst();
        }

        StdOut.println("\nThe final deque size: " + deque.size());
    }
}