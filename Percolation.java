/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int rowWidth;
    private int cellCount;

    private int virtualTopNode;
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
        
        openNeighbor(cellIndex, row - 1, col);
        openNeighbor(cellIndex, row + 1, col);
        openNeighbor(cellIndex, row, col - 1);
        openNeighbor(cellIndex, row, col + 1);
    }
    
    private void openNeighbor(int cellIndex, int neighborRow, int neighborCol) {
        try {
            int neighborIndex = mapCoordinateToIndex(neighborRow, neighborCol);

            if (openStates[neighborIndex]) {
                fillUnionUF.union(neighborIndex, cellIndex);
                percolateUnionUF.union(neighborIndex, cellIndex);
            }
        } catch (IllegalArgumentException e) {
            // ignore the neighbor index if it is invalid
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
    public static void main(String[] args) { }

    /**
     * Helper method to convert coordinates to 1-based index
     * (0, 0) maps to 1 to avoid setting it to virtual top node
     */
    private int mapCoordinateToIndex(int row, int col) {
        int rowIndex = row - 1;
        int colIndex = col - 1;

        if (rowIndex > rowWidth - 1 || colIndex > rowWidth - 1 || rowIndex < 0 || colIndex < 0) {
            throw new IllegalArgumentException();
        }

        return (rowIndex * rowWidth) + colIndex + 1;
    }
}
