
//package Module2;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean openSpots[];
    private int numOfOpenSpots =  0;
    private WeightedQuickUnionUF grid;
    private WeightedQuickUnionUF gridNoEndNode;
    private int n;
    private int arraySize;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        // Error Checking : n cannot be zero or a negative
        if (n <= 0) { throw new IllegalArgumentException("n must be greater than 0.");}

        // Added two extra elements to define the head and tail nodes
        int arraySize = ( n * n ) + 2;

        // INIT
        this.grid = new WeightedQuickUnionUF(arraySize);
        this.gridNoEndNode = new WeightedQuickUnionUF(arraySize);
        this.openSpots = new boolean[arraySize];
        this.n = n;
        this.arraySize = arraySize;

        // Head
        this.openSpots[0] = true;
        // Tail
        this.openSpots[arraySize-1] = true;
    };

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){

        // Get index from the coordinates  
        int currIndex = this.currentLocation(row,col);
        if (!isOpen(row,col)) {
            this.openSpots[currIndex] = true;
            numOfOpenSpots++;
        }

        if (row == 1) {
            grid.union(0,currIndex);
            gridNoEndNode.union(0,currIndex);
        }
        if (row == n) {
            grid.union(arraySize-1,currIndex);  
        }
        
        runUnion(row-1, col, currIndex);
        runUnion(row+1, col, currIndex);
        runUnion(row, col+1, currIndex);
        runUnion(row, col-1, currIndex);

    };


    //
    private void runUnion(int row, int col,int currlocation){
        if (row <= n && row > 0 && col > 0 && col <= n ){
            if ( isOpen(row,col) ) {
                grid.union(this.currentLocation(row,col),currlocation);
                gridNoEndNode.union(this.currentLocation(row,col),currlocation);
            }
        }
    }

    // A function that converts the (x,y) system into the actually postion the grid is located in within the array.
    private int currentLocation(int row, int col){
        // Check if coordinates is out of bounds
        if (row < 1 || row > n) {
            throw new IllegalArgumentException("Row is out of bounds.");
        }
        if (col < 1 || col > n) {
            throw new IllegalArgumentException("Column is out of bounds.");
        }
        else return (((row - 1) * n) + col);
    }
    // Check is the grid postion is open
    public boolean isOpen(int row, int col){
        return this.openSpots[this.currentLocation(row, col)];
    };

    // Checks if the grid postion is considered "Full". IE can reach the head node.
    public boolean isFull(int row, int col){
        return gridNoEndNode.find(0) == gridNoEndNode.find(currentLocation(row, col));
    };

    // Returns the number of open sites
    public int numberOfOpenSites() {
        return this.numOfOpenSpots;
    };

    // Checks if the current model percolates
    public boolean percolates(){
        return grid.find(arraySize-1) == grid.find(0);
    };

    public static void main(String[] args) {
    }
}
