public class BruteCollinearPoints {

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        validatePoints(points);

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    Point p = points[i];
                    Point q = points[j];
                    Point r = points[k];

                    if (checkCollinear(p, q, r)) {

                        for (int l = k + 1; l < points.length; l++) {
                            Point s = points[l];

                            if (checkCollinear(p, q, s)) {

                            }
                        }
                    }
                }
            }
        }
    }

    private boolean checkCollinear(Point p0, Point p1, Point p2) {
        return Double.compare(p0.slopeTo(p1), p0.slopeTo(p2)) == 0
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

    }

    // the line segments
    public LineSegment[] segments() {

    }
}