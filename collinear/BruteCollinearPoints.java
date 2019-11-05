/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.List;


public class BruteCollinearPoints {
    private Point[] pi;
    private LineSegment[] a;
    private int count;

    private void findSegment() {
        int n = pi.length;
        int count = 0;
        List<LineSegment> list = new ArrayList<LineSegment>();
        for (int p = 0; p < n; p++) {
            for (int q = p + 1; q < n; q++) {
                for (int r = q + 1; r < n; r++) {
                    for (int s = r + 1; s < n; s++) {
                        Point p1 = pi[p];
                        Point p2 = pi[q];
                        Point p3 = pi[r];
                        Point p4 = pi[s];
                        double s1 = p1.slopeTo(p2);
                        double s2 = p1.slopeTo(p3);
                        double s3 = p3.slopeTo(p4);
                        if (s1 == s2 && s2 == s3 && s1 == s3) {
                            Point smallest = smaller(smaller(p1, p2), smaller(p3, p4));
                            Point biggest = bigger(bigger(p1, p2), bigger(p3, p4));
                            list.add(new LineSegment(smallest, biggest));
                            count++;
                        }
                    }
                }
            }
        }

        a = new LineSegment[list.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = list.get(i);
        }
        this.count = count;

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

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
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
        findSegment();
    }

    private Point smaller(Point a, Point b) {
        if (a.compareTo(b) > 0) return b;
        else return a;
    }

    private Point bigger(Point c, Point d) {
        if (c.compareTo(d) > 0) return c;
        else return d;
    }

    public int numberOfSegments()        // the number of line segments
    {
        //FindSegment();
        int res = this.count;
        return res;
    }


    public LineSegment[] segments()                // the line segments
    {
        //FindSegment();
        LineSegment[] res = new LineSegment[this.a.length];
        System.arraycopy(this.a, 0, res, 0, this.a.length);
        return res;
    }
}
