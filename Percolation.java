/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int rowWidth;
    private WeightedQuickUnionUF quickUnionUF;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n cannot be less than 0");

        rowWidth = n;
        int cellCount = n * n + 2;

        quickUnionUF = new WeightedQuickUnionUF(cellCount);

        int virtualTopNode = 0;
        int virtualLastNode = cellCount - 1;

        for (int i = 0; i < rowWidth; i++) {
            int topRowCell = mapCoordinateToIndex(0, i);
            int bottomRowCell = mapCoordinateToIndex(rowWidth - 1, i);

            quickUnionUF.union(virtualTopNode, topRowCell);
            quickUnionUF.union(virtualLastNode, bottomRowCell);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) { }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) { return false; }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) { return false; }

    // returns the number of open sites
    public int numberOfOpenSites() { return 0;  }

    // does the system percolate?
    public boolean percolates() { return false; }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);
    }

    /*
    (0, 0) will map to the  virtual top at index 0
     */
    private int mapCoordinateToIndex(int row, int col) {
        if (row > rowWidth - 1 || col > rowWidth - 1) throw new IllegalArgumentException();

        return (row * rowWidth) + col + 1;
    }
}
