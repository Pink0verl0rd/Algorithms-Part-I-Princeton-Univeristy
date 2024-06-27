import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;



public class Percolation {

    private int gridLength;
    private int numberOfOpenSites;
    private int grid[];
    private boolean open[];

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        grid = new int [(n*n)];
        open = new boolean[(n*n)];
        this.gridLength = n;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        int n = (row + col*gridLength);
        open[n] = true;
        numberOfOpenSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() { 
        return this.numberOfOpenSites; 
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}