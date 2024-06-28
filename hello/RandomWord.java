package hello;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
 * Takes a line of words as input. Will Randommly choose a word from said input to redisplay
 * Requires a end of line for the second input
 */
public class RandomWord {
    public static void main(String[] args) {
        String value = "";
        
        for (int i = 0; !StdIn.isEmpty(); i++) {

            // p is the Probability of the word replacing the last saved word.
            double p = (double) 1 / (i + 1);

            String newvalue = StdIn.readString();

            if (StdRandom.bernoulli(p)) {
                value = newvalue;
            }
        }
        StdOut.print(value);
    } 
}

