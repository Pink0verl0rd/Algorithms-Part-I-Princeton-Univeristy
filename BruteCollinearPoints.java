import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    
    private final static int MAXPOINTS = 4;
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        List<LineSegment> list = new LinkedList<>();

        for (int i = 0; i < points.length &&  (points.length - i) >= MAXPOINTS; i++){
            Point head = points[i];
            double slopes[] = new double[MAXPOINTS-1];
            boolean segmentExist = true;
            int j = i + 1;

            for (int k = 0; (j - i) < MAXPOINTS ; j++, k++) {
                slopes[k] = head.slopeTo(points[j]);
                if (k > 0) {
                    if (slopes[0] != slopes[k]){
                        segmentExist = false;
                        break;
                    }
                }
            }   

            if (segmentExist){
                list.add(new LineSegment(head, points[j-1]));
                
            } 
        }

        segments = list.toArray(new LineSegment[0]);

    } 
    public int numberOfSegments() {return segments.length;}        // the number of line segments
    public LineSegment[] segments() { return segments;}                // the line segments


    public static void main(String[] args) {

        // read the n points from a file
        // In in = new In(args[0]);
        In in = new In("points.csv");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 10);
        StdDraw.setYscale(0, 10);
        StdDraw.setPenRadius(0.05);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        //print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
             StdOut.println(segment);
             segment.draw();
        }
        // StdDraw.show();
        int kappa=0;    
    }
}