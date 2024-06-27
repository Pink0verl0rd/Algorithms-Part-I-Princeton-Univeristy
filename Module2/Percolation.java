package Module2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean openSpots[];
    private int numOfOpenSpots =  0;
    private WeightedQuickUnionUF grid;
    private int gridLengthHeight;
    private int maxSize;

    

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        int fullsize = (n*n) + 2;

        try {
            this.grid = new WeightedQuickUnionUF(fullsize);
            this.openSpots = new boolean[fullsize];
            for (int i = 0 ; i < this.openSpots.length ; i++){
                this.openSpots[i] = false;
            }
            this.gridLengthHeight = n;
            this.maxSize = fullsize;

            for (int i = 0; i < n ; i++){
                grid.union(0,i+1);
                grid.union((fullsize-1), (( fullsize - 1 ) - ( i + 1 )));
            }

            this.openSpots[0] = true;
            this.openSpots[fullsize-1] = true;
        }
        catch(Exception e) {
            throw e;
        }
        
    };

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){

        int currlocation = this.currentLocation(row,col);

        if (!isOpen(row,col)) {
            this.openSpots[currlocation] = true;
            numOfOpenSpots++;
        }
        
        runUnion(row-1, col, currlocation);
        runUnion(row+1, col, currlocation);
        runUnion(row, col+1, currlocation);
        runUnion(row, col-1, currlocation);

    };

    public void runUnion(int row, int col,int currlocation){
        if (row <= gridLengthHeight && row > 0 && col > 0 && col <= gridLengthHeight ){
            if ( isOpen(row,col) ) {
                grid.union(this.currentLocation(row,col),currlocation);
            }
        }
    }

    private int currentLocation(int row, int col){
        return (((row - 1) * gridLengthHeight) + col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        try {
            int currentLocation = this.currentLocation(row, col);
            return this.openSpots[currentLocation];
        } catch (Exception e) {
            throw e;
        }
    };

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        return false;
    };

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numOfOpenSpots;
    };

    // does the system percolate?
    public boolean percolates(){
        return 0 == grid.find(maxSize-1);
    };

    // test client (optional)
    public static void main(String[] args) {

        int size = 3;
         Percolation val = new Percolation(size);

        do{
            val.open(StdRandom.uniformInt(3) + 1, StdRandom.uniformInt(3) + 1);
        } while(!val.percolates());

        System.out.println(val.numberOfOpenSites());
    }
}