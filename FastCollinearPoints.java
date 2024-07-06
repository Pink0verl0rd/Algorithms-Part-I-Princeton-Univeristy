import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private final static int MAXSLOPES = 3;
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        List<LineSegment> list = new LinkedList<>();
        int N = points.length;

        Point[] sortedPoints = points.clone();
        double[] slopes = new double[points.length];
        Point pp = sortedPoints[1];

        for (int j = 0 ; j < N ; j++) {

        }

        Arrays.sort(sortedPoints);
        for (Point p : sortedPoints) {
            StdOut.println(p.toString());
        }
        StdOut.println('\n');

        // Arrays.sort(sortedPoints, 0, N, sortedPoints[0].slopeOrder());
        // for (Point p : sortedPoints) {
        //     StdOut.println(p.toString());
        // }
        // StdOut.println('\n');

        Arrays.sort(sortedPoints, 0, N, sortedPoints[1].slopeOrder());
        for (Point p : sortedPoints) {
            StdOut.println(p.toString());
        }
        StdOut.println('\n');

        for (int i = 0; i < slopes.length; i++) {
            slopes[i] = pp.slopeTo(sortedPoints[i]);
        }

        // double prevSlope = -1;
        int prevSlopeI = 0;

        for (int i = 1,c = 0; i < slopes.length; i++) {
            double currSlope = slopes[i];
            double prevSlope = slopes[prevSlopeI];
            
            if (prevSlope == currSlope) c++;
            else {
                if (c >= MAXSLOPES) {
                    list.add(new LineSegment(sortedPoints[prevSlopeI - 1], sortedPoints[i - 1]));
                }
                c = 0;
                prevSlopeI = i;
            };

        }
        
        segments = list.toArray(new LineSegment[0]);

    } 
    public int numberOfSegments() {return segments.length;}        // the number of line segments
    public LineSegment[] segments() { return segments;}                // the line segments

    public static void main(String[] args) {

    // read the n points from a file
    In in = new In("input2.txt");
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
    }

    // draw the points
    Arrays.sort(points);
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    StdDraw.setPenRadius(0.025);
    for (Point p : points) {
        p.draw();
        StdDraw.show();
    }
    

    // print and draw the line segments
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
    }
 }