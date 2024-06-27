

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double openlost[];
    private static double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        
        if (n <= 0) {
            throw new IllegalArgumentException("n <= 0.");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("trials <= 0.");
        }

        openlost = new double[trials];
        for(int i = 0 ; i < trials; i++){
            Percolation run = new Percolation(n);
            do {

                run.open(StdRandom.uniformInt(n) + 1, StdRandom.uniformInt(n) + 1);
                // PercolationVisualizer.draw(run,n);
           
            } while(!run.percolates());

            openlost[i] = (double) run.numberOfOpenSites() / ( n * n);
            
        }   
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(openlost);
    }

    // sample standard deviation of percolation threshold
    public double stddev()  {
        return StdStats.stddev(openlost);}

    // low endpoint of 95% confidence interval
    public double confidenceLo()  {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(openlost.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()  {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(openlost.length);
    }

   // test client (see below)
   public static void main(String[] args){
        PercolationStats test = new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        StdOut.println("mean                    = " + test.mean());
        StdOut.println("stddev                  = " + test.stddev());
        StdOut.println("95% confidence interval = [" + test.confidenceLo() + ", " + test.confidenceHi() + "]");
        
   }

}