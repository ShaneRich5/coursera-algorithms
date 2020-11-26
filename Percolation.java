/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int rowWidth;
    private int cellCount;

    private int virtualTopNode = 0;
    private int virtualBottomNode;

    private boolean[] openStates;
    private WeightedQuickUnionUF fillUnionUF;
    private WeightedQuickUnionUF percolateUnionUF;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) throw new IllegalArgumentException("n cannot be less than 0");

        rowWidth = n;
        cellCount = n * n + 2;
        virtualBottomNode = cellCount - 1;

        openStates = new boolean[cellCount];
        
        fillUnionUF = new WeightedQuickUnionUF(cellCount);
        percolateUnionUF = new WeightedQuickUnionUF(cellCount);

        openStates[virtualTopNode] = true;
        openStates[virtualBottomNode] = true;

        for (int i = 0; i < rowWidth; i++) {
            int topRowCell = mapCoordinateToIndex(1, i + 1);
            int bottomRowCell = mapCoordinateToIndex(rowWidth, i + 1);

            fillUnionUF.union(virtualTopNode, topRowCell);
            
            percolateUnionUF.union(virtualTopNode, topRowCell);
            percolateUnionUF.union(virtualBottomNode, bottomRowCell);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int cellIndex = mapCoordinateToIndex(row, col);
        openStates[cellIndex] = true;

        int[][] neighbours = {{ row - 1, col }, { row + 1, col }, { row, col + 1}, { row, col - 1 }};

        for (int i = 0; i < neighbours.length; i++) {
            int[] neighbourCoordinate = neighbours[i];

            int rowIndex = neighbourCoordinate[0];
            int colIndex = neighbourCoordinate[1];

            try {
                int neighbourIndex = mapCoordinateToIndex(rowIndex, colIndex);

                if (openStates[neighbourIndex]) {
                    fillUnionUF.union(neighbourIndex, cellIndex);
                    percolateUnionUF.union(neighbourIndex, cellIndex);
                }
            } catch (IllegalArgumentException e) { }
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int index = mapCoordinateToIndex(row, col);
        return openStates[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) {
            return false;
        }

        int index = mapCoordinateToIndex(row, col);
        return fillUnionUF.connected(virtualTopNode, index);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int counter = 0;

        for (int i = 0; i < cellCount; i++) {
            if (openStates[i]) counter++;
        }

        return counter;
    }

    // does the system percolate?
    public boolean percolates() { return percolateUnionUF.connected(virtualTopNode, virtualBottomNode); }

    // test client (optional)
    public static void main(String[] args) {

    }

    /*
    (0, 0) will map to the  virtual top at index 0
     */
    private int mapCoordinateToIndex(int row, int col) {
        int rowIndex = row - 1;
        int colIndex = col - 1;

        // System.out.println("row: " + row + " col:  " + col + " rowIndex: " + rowIndex + " colIndex: " + colIndex);
        System.out.println("row: " + row + " col:  " + col + " rowIndex: " + rowIndex + " colIndex: " + colIndex);

        if (rowIndex > rowWidth - 1 || colIndex > rowWidth - 1 || rowIndex < 0 || colIndex < 0) {
            throw new IllegalArgumentException();
        }

        return (rowIndex * rowWidth) + colIndex + 1;
    }
}
