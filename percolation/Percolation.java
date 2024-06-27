
//package Module2;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean openSpots[];
    private int numOfOpenSpots =  0;
    private WeightedQuickUnionUF grid;
    private WeightedQuickUnionUF backwashQU;
    private int n;
    private int arraySize;
    private boolean doesPercolates;

    

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        // Error Checking : n cannot be zero or a negative
        if (n <= 0) { throw new IllegalArgumentException("n must be greater than 0.");}

        // Added two extra elements to define the head and tail nodes
        int arraySize = ( n * n ) + 2;

        // INIT
        this.grid = new WeightedQuickUnionUF(arraySize);
        this.backwashQU = new WeightedQuickUnionUF(arraySize);
        this.openSpots = new boolean[arraySize];
        this.n = n;
        this.arraySize = arraySize;
        this.doesPercolates = false;

        // Head
        this.openSpots[0] = true;
        // Tail
        this.openSpots[arraySize-1] = true;
    };

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){

        // Get index from the corrdicntest 
        int currIndex = this.currentLocation(row,col);

        if (!isOpen(row,col)) {
            this.openSpots[currIndex] = true;
            numOfOpenSpots++;
        }

        if (row == 1) {
            grid.union(0,currIndex);
            backwashQU.union(0,currIndex);
        }
        if (row == n) {
            grid.union(arraySize-1,currIndex);  
        }
        
        runUnion(row-1, col, currIndex);
        runUnion(row+1, col, currIndex);
        runUnion(row, col+1, currIndex);
        runUnion(row, col-1, currIndex);

    };

    private void runUnion(int row, int col,int currlocation){
        if (row <= n && row > 0 && col > 0 && col <= n ){
            if ( isOpen(row,col) ) {
                grid.union(this.currentLocation(row,col),currlocation);
                backwashQU.union(this.currentLocation(row,col),currlocation);
            }
        }
    }

    private int currentLocation(int row, int col){
        // check bounds
        if (row < 1 || row > n) {
            throw new IllegalArgumentException("Row is out of bounds.");
        }
        if (col < 1 || col > n) {
            throw new IllegalArgumentException("Column is out of bounds.");
        }
        return (((row - 1) * n) + col);
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        try {
            int currentLocation = this.currentLocation(row, col);
            return this.openSpots[currentLocation];
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    };

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        return backwashQU.find(0) == backwashQU.find(currentLocation(row, col));
    };

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numOfOpenSpots;
    };

    // does the system percolate?
    public boolean percolates(){
        return grid.find(arraySize-1) == grid.find(0);
    };

    // test client (optional)
    public static void main(String[] args) {

        // int n = 6;

        


        // Percolation val = new Percolation(n);
        
        // val.open(1, 1);
        // val.open(6, 2);
        // val.open(5, 2);
        // val.open(4, 2);
        // val.open(3, 2);
        // val.open(2, 2);
        // PercolationVisualizer.draw(val,n);
        
        // val.open(1, 2);
        
        // PercolationVisualizer.draw(val,n);
        
    }
}
