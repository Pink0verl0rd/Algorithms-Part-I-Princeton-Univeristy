import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    

    public static void main(String[] args) {
        String value = "";
        
        for (int i = 0; !StdIn.isEmpty(); i++) {
            double p = (double) 1 / (i + 1);
            String newvalue = StdIn.readString();

            if (StdRandom.bernoulli(p)) {
                value = newvalue;
            }
        }
        StdOut.print(value);
    } 
}

