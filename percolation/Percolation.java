/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private boolean sites[][];
    private WeightedQuickUnionUF uf;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1)
            throw new java.lang.IllegalArgumentException();
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        this.sites = new boolean[n][n];
        this.n = n;
    }

    private void checkArguments(int row, int col) {
        if (row < 1 || row > this.n || col < 1 || col > this.n)
            throw new java.lang.IllegalArgumentException();
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkArguments(row, col);
        row--;
        col--;


        if (sites[row][col]) return;
        if (!sites[row][col]) sites[row][col] = true;
        int num = getUnionNumber(row, col);
        if (row > 0 && sites[row - 1][col]) {
            uf.union(num, getUnionNumber(row - 1, col));
        }
        if (row < n - 1 && sites[row + 1][col]) {
            uf.union(num, getUnionNumber(row + 1, col));
        }
        if (col > 0 && sites[row][col - 1]) {
            uf.union(num, getUnionNumber(row, col - 1));
        }
        if (col < n - 1 && sites[row][col + 1]) {
            uf.union(num, getUnionNumber(row, col + 1));
        }
        if (row == 0) uf.union(0, num);
        if (row == n - 1) uf.union(num, n * n + 1);
    }

    private int getUnionNumber(int x, int y) {
        return x * n + y + 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkArguments(row, col);
        row--;
        col--;
        return sites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkArguments(row, col);
        row--;
        col--;
        return uf.connected(0, getUnionNumber(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (sites[i][j]) count++;
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0, (n * n + 1));
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        StdRandom.uniform(0, n);
        Percolation perco = new Percolation(n);
        while (!perco.percolates()) {
            int row = StdRandom.uniform(0, n);
            int col = StdRandom.uniform(0, n);
            perco.open(row, col);
        }
        StdOut.println(perco.numberOfOpenSites());
    }
}
