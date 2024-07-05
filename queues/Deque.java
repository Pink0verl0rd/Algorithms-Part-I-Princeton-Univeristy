package queues;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;

        public Node(Item item) {
            this.item = item;
        }
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = head.next;

        public boolean hasNext() { return current != tail;}

        public Item next() {

            if (current.next == null) { throw new NoSuchElementException("Iterator has no new node");}
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove unsupported.");
        }
    }

    // construct an empty deque
    public Deque(){
        // Make head and tail
        head = new Node(null); 
        tail = new Node(null); 
        // Have them point to each other
        head.next = tail;
        tail.prev = head;

        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() { return this.size == 0; }

    // return the number of items on the deque
    public int size() { return this.size; }

    // add the item to the front
    public void addFirst(Item item) {

        if (item == null){
            throw new IllegalArgumentException("addFirst argument as null is invalid");
        }

        // Make new Node
        Node current = new Node(item);
        // Linking 3 -> 2 / 2 -> 3 / 2 -> 1 / 1 -> 2
        head.next.prev = current;
        current.next = head.next;
        current.prev = head;
        head.next = current;

        size++;

    }

    // add the item to the back
    public void addLast(Item item) {

        if (item == null){
            throw new IllegalArgumentException("addFirst argument as null is invalid");
        }

        // Make new Node
        Node current = new Node(item);
        // Linking 3 -> 2 / 2 -> 3 / 2 -> 1 / 1 -> 2
        tail.prev.next = current;
        current.prev = tail.prev;
        current.next = tail;
        tail.prev = current;

        size++;
                
    }

    // remove and return the item from the front
    public Item removeFirst() {

        if ( size == 0 ) {throw new NoSuchElementException("Deque is currently empty");}

        Node current = head.next;
        head.next = current.next;
        current.next.prev = head;

        size--;

        return current.item;
    }

    // remove and return the item from the back
    public Item removeLast() {

        if ( size == 0 ) {throw new NoSuchElementException("Deque is currently empty");}
                
        Node current = tail.prev;
        tail.prev = current.prev;
        current.prev.next = tail;

        size--;

        return current.item;
    }

    // public String toString() {
    //     String result = "";
    //     for (Item item : this) {
    //         result += "," + item;
    //     }
    //     if (!result.isEmpty()) {
    //         result = result.substring(1);
    //     }
    //     return "[" + result + "]";
    // }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() { return new ListIterator();}
    // unit testing (required)
    public static void main(String[] args) {
        

    //     StdOut.println("Tests start.");

    //     // Test 1: public operations
    //     Deque<Integer> d1 = new Deque<>();
    //     StdOut.println("Test 1A passed? " + d1.isEmpty());
    //     StdOut.println("Test 1B passed? " + d1.toString().equals("[]"));
        
    //     d1.addLast(1);
    //     d1.addLast(2);

    //     StdOut.println("DEBUG " + d1.toString());
    //     StdOut.println("Test 1C passed? " + d1.toString().equals("[1,2]"));
    //     StdOut.println("Test 1D passed? " + (d1.size() == 2));
    //     StdOut.println("Test 1E passed? " + (d1.iterator().next() == 1));
    //     d1.addFirst(0);
    //     StdOut.println("Test 1F passed? " + d1.toString().equals("[0,1,2]"));
    //     d1.removeLast();
    //     StdOut.println("Test 1G passed? " + d1.toString().equals("[0,1]"));
    //     d1.removeFirst();
    //     StdOut.println("Test 1H passed? " + d1.toString().equals("[1]"));
    //     d1.removeFirst();
    //     StdOut.println("Test 1I passed? " + d1.toString().equals("[]"));
    //     StdOut.println("Test 1J passed? " + d1.isEmpty());
    //     StdOut.println("Test 1H passed? " + !d1.iterator().hasNext());

    //     // Test 2: exceptions
    //     Deque<Integer> d2 = new Deque<>();
    //     try {
    //         d2.removeFirst();
    //         StdOut.println("Test 2A passed? " + false);
    //     } catch (Exception e) {
    //         boolean result = e instanceof NoSuchElementException;
    //         StdOut.println("Test 2A passed? " + result);
    //     }
    //     try {   
    //         d2.removeLast();
    //         StdOut.println("Test 2B passed? " + false);
    //     } catch (Exception e) {
    //         boolean result = e instanceof NoSuchElementException;
    //         StdOut.println("Test 2B passed? " + result);
    //     }
    //     try {
    //         d2.addFirst(null);
    //         StdOut.println("Test 2C passed? " + false);
    //     } catch (Exception e) {
    //         boolean result = e instanceof NullPointerException;
    //         StdOut.println("Test 2C passed? " + result); 
    //     }
    //     try {
    //         d2.addLast(null);
    //         StdOut.println("Test 2D passed? " + false); 
    //     } catch (Exception e) {
    //         boolean result = e instanceof NullPointerException;
    //         StdOut.println("Test 2D passed? " + result);
    //     }
    //     try {
    //         d2.iterator().remove();
    //         StdOut.println("Test 2F passed? " + false);
    //     } catch (Exception e) {
    //         boolean result = e instanceof UnsupportedOperationException;
    //         StdOut.println("Test 2F passed? " + result);
    //     }
    //     try {
    //         d2.iterator().next();
    //         StdOut.println("Test 2G passed? " + false);
    //     } catch (Exception e) {
    //         boolean result = e instanceof NoSuchElementException;
    //         StdOut.println("Test 2G passed? " + result);
    //     }

    //     // Test 3: types
    //     Deque<String> d3a = new Deque<>();
    //     d3a.addFirst("Hello Algorithm");
    //     StdOut.println("Test 3A passed? " + true);
    //     Deque<Double> d3b = new Deque<>();
    //     d3b.addLast(3.1415926);
    //     StdOut.println("Test 3B passed? " + true);

    //     StdOut.println("Tests finished.");

    }

}