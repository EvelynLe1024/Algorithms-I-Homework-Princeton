/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int trials;
    private double[] a;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (trials < 1) throw new java.lang.IllegalArgumentException();
        this.trials = trials;
        this.a = new double[trials];
        a = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                p.open(row, col);
            }
            a[i] = (double) p.numberOfOpenSites() / (double) (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(a);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(a);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double lo = this.mean() - (1.96 * this.stddev()) / Math.sqrt(trials);
        return lo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double hi = this.mean() + (1.96 * this.stddev()) / Math.sqrt(trials);
        return hi;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, T);
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println(
                "95% confidence interval = " + "[" + ps.confidenceLo() + ", " + ps.confidenceHi()
                        + "]");
    }

}
