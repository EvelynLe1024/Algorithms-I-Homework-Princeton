/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.List;

public class FastCollinearPoints {
    private Point[] pi;
    private LineSegment[] a;
    private int count;
    private double[] array = new double[pi.length];

    private void findSegmentFast() {
        int n = pi.length;
        List<LineSegment> list = new ArrayList<LineSegment>();

        for (int i = 0; i < n; i++) {
            int j = 0;
            while (j < n) {
                double s = pi[i].slopeTo(pi[j]);
                int k = j++;
                while (k < n && pi[i].slopeTo(pi[k]) == s) {
                    k++;
                    if (k - j >= 3) {
                        list.add(new LineSegment(pi[j], pi[k]));
                        count++;
                    }
                }
            }

        }

        /* for (int i = 0; i < n; i++) {
            Arrays.sort(pi, pi[i].slopeOrder());
            for (int j = 0; j < n; j++) {
                if (pi[i].slopeTo(pi[j]) == pi[i].slopeTo(pi[j + 2])) {
                    Point smallest = smaller(smaller(pi[i], pi[j]), smaller(pi[j + 1], pi[j + 2]));
                    Point biggest = bigger(bigger(pi[i], pi[j]), bigger(pi[j + 1], pi[j + 2]));
                    list.add(new LineSegment(smallest, biggest));
                    count++;
                }

            }
        }
        */
        a = new LineSegment[list.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = list.get(i);
        }


    }

    private Point smaller(Point a, Point b) {
        if (a.compareTo(b) > 0) return b;
        else return a;
    }

    private Point bigger(Point c, Point d) {
        if (c.compareTo(d) > 0) return c;
        else return d;
    }

    private void checkNullPoint(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException("Argument is null.");
        }
    }

    private void checkPointOverlap(Point[] points) {
        int n = points.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (points[i].equals(points[j]))
                    throw new IllegalArgumentException("Repeated points");
            }
        }
    }

    public FastCollinearPoints(
            Point[] points)     // finds all line segments containing 4 or more points
    {
        if (points == null) throw new NullPointerException("Empty array");
        checkNullPoint(points);
        checkPointOverlap(points);
        if (points.length < 4) {
            this.count = 0;
            this.a = new LineSegment[0];
            return;
        }
        this.pi = points;
        findSegmentFast();
    }

    public int numberOfSegments()        // the number of line segments
    {
        int res = this.count;
        return res;
    }

    public LineSegment[] segments()                // the line segments
    {
        LineSegment[] res = new LineSegment[this.a.length];
        System.arraycopy(this.a, 0, res, 0, this.a.length);
        return res;
    }
}
