/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    int n;
    int[][] tiles;
    int count;
    int sum;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        tiles = new int[n][n];
    }

    // string representation of this board
    public String toString() {
        StdOut.println(n);
        StdOut.print(0);

        for (int i = 0; i <= n * n - 1; i++) {
            StdOut.print(i);
            if (i > 0 && i % n == 0) StdOut.println();
        }
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        Board b = new Board(tiles);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = StdIn.readInt();
                if (tiles[i][j] != i * n + j) count++;
            }
        }
        this.count = count;
        return this.count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int sum = 0;
        Board board_m = new Board(tiles);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j] = StdIn.readInt();
                sum += b[i][j] != i * n + j;
            }
        }
        this.count = count;
        return this.count;
    }

    // is this board the goal board?
    public boolean isGoal()

    // does this board equal y?
    public boolean equals(Object y)

    // all neighboring boards
    public Iterable<Board> neighbors()

    // a board that is obtained by exchanging any pair of tiles
    public Board twin()

    // unit testing (not graded)
    public static void main(String[] args)

}
