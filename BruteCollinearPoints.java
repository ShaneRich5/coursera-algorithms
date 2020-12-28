import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private LineSegment[] lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        validatePoints(points);
        Arrays.sort(points);
        findSegments(points);
    }

    private void findSegments(Point[] points) {
        List<LineSegment> result = new ArrayList<LineSegment>();

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    Point p = points[i];
                    Point q = points[j];
                    Point r = points[k];

                    if (checkCollinear(p, q, r)) {

                        for (int m = k + 1; m < points.length; m++) {
                            Point s = points[m];

                            if (checkCollinear(p, q, s)) {
                                result.add(new LineSegment(p, s));
                            }
                        }
                    }
                }
            }
        }

        lineSegments = result.toArray(new LineSegment[result.size()]);
    }

    private boolean checkCollinear(Point p0, Point p1, Point p2) {
        return Double.compare(p0.slopeTo(p1), p0.slopeTo(p2)) == 0;
    }

    private void validatePoints(Point[] points)  {
        if (points == null) throw new IllegalArgumentException("Points cannot be null!");

        for (int i = 0; i < points.length; i++) {
            Point p0 = points[i];

            if (p0 == null) throw new IllegalArgumentException("Point cannot be null!");

            for (int j = i; j < points.length; j++) {
                Point p1 = points[j];

                if (p0.compareTo(p1) == 0) throw new IllegalArgumentException("Points cannot contain repeated points!");
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegments;
    }
}