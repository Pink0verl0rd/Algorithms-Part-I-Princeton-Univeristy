package queues;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item array[];
    private int tail;
    private final static double MINRESIZE = 0.25;

    // construct an empty randomized queue
    public RandomizedQueue() {

        Item a[] = (Item[]) new Object[1];
        array = a;
        tail = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() { return size() == 0; }

    // return the number of items on the randomized queue
    public int size() { return tail; }

    // add the item
    public void enqueue(Item item) {

        if (item == null){
            throw new IllegalArgumentException("addFirst argument as null is invalid");
        }

        if (tail == array.length) resize(array.length * 2);

        array[tail] = item;

        tail++;
    }

    // remove and return a random item
    public Item dequeue() {

        if (isEmpty()) {throw new NoSuchElementException("Deque is currently empty");}

        int n = StdRandom.uniformInt(tail);
        Item item = array[n];
        array[n] = array[tail - 1];
        array[tail - 1] = null;
        tail--;
        if (size() > 0 && tail < (array.length * MINRESIZE)) {resize(array.length / 2);}
        
        return item;
    }

    private void resize(int capacity) {
        int currentLength = this.size();

        Item newArray[] = (Item[]) new Object[(capacity)];

        for (int i = 0; i < currentLength ; i++){
            newArray[i] = this.array[i];
        }

        this.array = newArray;
    }

    // return a random item (but do not remove it)
    public Item sample() {

        if (isEmpty()) {throw new NoSuchElementException("Deque is currently empty");}

        return array[StdRandom.uniformInt(tail)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {return new ListIterator();}

    private class ListIterator implements Iterator<Item> {

        private int arrayRandomIndices[];
        private int n;

        ListIterator(){
            arrayRandomIndices = new int[tail];
            for (int i = 0; i < tail ; i++){
                arrayRandomIndices[i] = i;
            }
            n = tail - 1;
        }

        public boolean hasNext() { return n < tail && n >= 0;}

        public Item next() {
            if (n < 0 || n >= tail) { throw new NoSuchElementException("Iterator has no new node");}
            else {
                int j = 0;
                if ( n > 0 ) j = StdRandom.uniformInt(n + 1);
                int i = arrayRandomIndices[j]; 

                Item item = array[i];
                

                arrayRandomIndices[j] = arrayRandomIndices[n];
                arrayRandomIndices[n] = -1;
                
                n--;
                return item;
            }
        }   

        public void remove() {
            throw new UnsupportedOperationException("Remove unsupported.");
        }

    }
    
    
    public String toString() {
        String result = "";

        
        if (this.isEmpty()){
            return "[]";
        }
        else {
            for (int i = 0; i < this.size() ; i++){
                result += "," + this.array[i].toString();
            }
        }

        if (!result.isEmpty()) {
            result = result.substring(1);
        }

        return "[" + result + "]";

    }
    
    // unit testing (required)
    public static void main(String[] args) {        

        // RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        // queue.enqueue(3);
        // queue.enqueue(0);
        // queue.enqueue(1);
        // queue.enqueue(1);
        

        // StdOut.println(q.next());

        // RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        // queue.enqueue(3);
        // queue.enqueue(1);
        // queue.enqueue(2);
        // Iterator<Integer> q = queue.iterator();
        // StdOut.println(q.next());
        // StdOut.println(q.next());
        // StdOut.println(q.next());


        // StdOut.println("Tests start.");

        // // Test 1: public opeations
        
        // StdOut.println("Test 1A passed? " + q1.isEmpty());
        // StdOut.println("Test 1B passed? " + q1.toString().equals("[]"));
        // q1.enqueue(1);
        // q1.enqueue(2);
        // StdOut.println("Test 1C passed? " + q1.toString().equals("[1,2]"));
        // StdOut.println("Test 1D passed? " + (q1.size() == 2));
        // int test1E = q1.iterator().next();
        // StdOut.println("Test 1E passed? " + (test1E == 1 || test1E == 2));
        // q1.enqueue(3);
        // q1.enqueue(4);
        // StdOut.println("Test 1F passed? " + q1.toString().equals("[1,2,3,4]"));
        // q1.dequeue();
        // String test1G = q1.toString();
        // StdOut.println("Test 1G passed? "
        //          + (test1G.equals("[4,2,3]")
        //          || test1G.equals("[1,4,3]")
        //          || test1G.equals("[1,2,4]")
        //          || test1G.equals("[1,2,3]")));
        // q1.dequeue();
        // q1.dequeue();
        // // Queue should be resized when 25% full: the size will be reduced by
        // // 50%. However, the unused elements in the new array are not visible
        // // because they're excluded by the {@code toString()} method.
        // String test1H = q1.toString();
        // StdOut.println("Test 1H passed? "
        //          + (test1H.equals("[1]")
        //          || test1H.equals("[2]")
        //          || test1H.equals("[3]")
        //          || test1H.equals("[4]")));
        // q1.dequeue();
        // StdOut.println("Test 1I passed? " + q1.toString().equals("[]"));
        // StdOut.println("Test 1J passed? " + q1.isEmpty());
        // StdOut.println("Test 1K passed? " + !q1.iterator().hasNext());
        // StdOut.println("Test 1L passed? " + (q1.iterator() != q1.iterator()));
        // q1.enqueue(1);
        // StdOut.println("Test 1M passed? " + q1.toString().equals("[1]"));
        // q1.enqueue(2);
        // StdOut.println("Test 1N passed? " + q1.toString().equals("[1,2]"));

        // // Test 2: exceptions
        // RandomizedQueue<Integer> q2 = new RandomizedQueue<>();
        // try {
        //     q2.dequeue();
        //     StdOut.println("Test 2A passed? " + false);
        // } catch (Exception e) {
        //     boolean result = e instanceof NoSuchElementException;
        //     StdOut.println("Test 2A passed? " + result);
        // }
        // try {
        //     q2.sample();
        //     StdOut.println("Test 2B passed? " + false); 
        // } catch (Exception e) {
        //     boolean result = e instanceof NoSuchElementException;
        //     StdOut.println("Test 2B passed? " + result);
        // }
        // try {
        //     q2.enqueue(null);
        //     StdOut.println("Test 2C passed? " + false); 
        // } catch (Exception e) {
        //     boolean result = e instanceof IllegalArgumentException;
        //     StdOut.println("Test 2C passed? " + result);
        // }
        // try {
        //     q2.iterator().remove();
        //     StdOut.println("Test 2D passed? " + false); 
        // } catch (Exception e) {
        //     boolean result = e instanceof UnsupportedOperationException;
        //     StdOut.println("Test 2D passed? " + result);
        // }
        // try {
        //     q2.iterator().next();
        //     StdOut.println("Test 2E passed? " + false);
        // } catch (Exception e) {
        //     boolean result = e instanceof NoSuchElementException;
        //     StdOut.println("Test 2E passed? " + result);
        // }

        // // Test 3: types
        // RandomizedQueue<String> q3A = new RandomizedQueue<>();
        // q3A.enqueue("Hello Algorithm");
        // StdOut.println("Test 3A passed? " + true);
        // RandomizedQueue<Double> q3B = new RandomizedQueue<>();
        // q3B.enqueue(3.1415926);
        // StdOut.println("Test 3B passed? " + true);

        // StdOut.println("Tests finished.");
        

        // StdOut.println("Public Functions");
        // // Test 1: public operations
        // RandomizedQueue<Integer> rq1 = new RandomizedQueue<>();

        // StdOut.println("Testing [isEmpty] : " + rq1.isEmpty());
        // StdOut.println("Testing [size] : " + (rq1.size() == 0));
        // rq1.enqueue(1);
        // StdOut.println("Testing [enqueue] : " + (rq1.toString()).equals("[1]"));
        // rq1.enqueue(2);
        // StdOut.println("Testing [enqueue] : " + (rq1.toString()).equals("[1,2]"));
        // StdOut.println("Testing [size] : " + (rq1.size() == 2));
        // rq1.enqueue(3);
        // rq1.enqueue(4);
        // StdOut.println("Testing [enqueue] : " + (rq1.toString()).equals("[1,2,3,4]"));
        // StdOut.println("Testing [size] : " + (rq1.size() == 4));
        // StdOut.println("Testing [isEmpty] : " + (rq1.isEmpty() == false));

        
        // StdOut.println("Testing [sample] : " + (rq1.sample()));
        // StdOut.println("Testing [sample] : " + (rq1.sample()));
        // StdOut.println("Testing [sample] : " + (rq1.sample()));
        // StdOut.println("Testing [sample] : " + (rq1.sample()));
        // StdOut.println("Testing [sample] : " + (rq1.sample()));
        // StdOut.println("Testing [sample] : " + (rq1.sample()));

        // rq1.dequeue();
        // StdOut.println("Testing [dequeue] : " + (rq1.toString()).equals("[2,3,4]"));
        // rq1.dequeue();
        // StdOut.println("Testing [dequeue] : " + (rq1.toString()).equals("[3,4]"));
        // rq1.dequeue();
        // StdOut.println("Testing [dequeue] : " + (rq1.toString()).equals("[4]"));
        // rq1.dequeue();
        // StdOut.println("Testing [dequeue] : " + (rq1.toString()).equals("[]"));
        

    }

}