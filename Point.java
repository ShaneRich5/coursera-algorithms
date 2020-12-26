import java.util.Comparator;

public class Point implements Comparable<Point> {
    public int x;
    public int y;

    // constructs the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // draws this point
    public void draw() {

    }

    // draws the line segment from this point to that point
    public void drawTo(Point that) {

    }

    // string representation
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (y < that.y) return -1;
        if (y > that.y) return 1;
        if (x < that.x) return -1;
        if (x > that.x) return 1;
        return 0;
    }

    // the slope between this point and that point
    public double slopeTo(Point that) {

    }

    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder() {

    }
}