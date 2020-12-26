package Percolation;/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException("n and trials must be greater than 0");
        }

        results = new double[trials];
        int totalNumberOfSites = n * n;

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);

            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);

                percolation.open(row, col);
            }

            results[i] = (double) percolation.numberOfOpenSites() / totalNumberOfSites;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - partialConfidence();
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + partialConfidence();
    }

    private double partialConfidence() {
        return ((1.96 * stddev()) / Math.sqrt(results.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);         // n-by-n percolation system
        int trails = Integer.parseInt(args[1]);    // number of trails

        PercolationStats stats = new PercolationStats(n, trails);

        double mean = stats.mean();
        double stddev = stats.stddev();
        double confidenceLo = stats.confidenceLo();
        double confidenceHi = stats.confidenceHi();

        String output = "mean\t\t\t\t\t= " + mean + "\n"
                + "stddev\t\t\t\t\t= " + stddev + "\n"
                + "95% confidence interval\t= [" + confidenceLo + ", " + confidenceHi + "]";
        System.out.println(
                output);
    }
}
