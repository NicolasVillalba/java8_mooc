package streams;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by valenciap on 23/07/2015.
 */
public class ClosestPoints {

    public class Point implements Comparable<Point>{

        private static final int X_REFERENCE = 0;
        private static final int Y_REFERENCE = 11;

        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public int compareTo(Point o) {

            double distanceOther  = Math.sqrt(Math.pow( o.getX() - X_REFERENCE,2) +
                    Math.pow( o.getY() - Y_REFERENCE,2));
            double distanceThis  = Math.sqrt(Math.pow(this.getX() - X_REFERENCE,2) +
                    Math.pow(this.getY() - Y_REFERENCE, 2));
            return new Double(distanceThis).compareTo(new Double(distanceOther));

        }

        @Override
        public String toString() {
            return "("+getX()+", "+getY()+")";
        }
    }

    public void calculateClosestPoints() {
        Point[] points = {new Point(0,0), new Point(1,1), new Point(3,2), new Point(13,15)};

        Arrays.asList(points).stream().sorted().limit(1).collect(Collectors.toList()).forEach(System.out::println);
    }

    public static void main(String[] args) {
        ClosestPoints closesPoints = new ClosestPoints();

        closesPoints.calculateClosestPoints();
    }
}
