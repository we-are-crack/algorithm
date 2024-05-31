import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        ArrayList<Point> points = new ArrayList<>();
        long maxX = Long.MIN_VALUE; long maxY = Long.MIN_VALUE;
        long minX = Long.MAX_VALUE; long minY = Long.MAX_VALUE;
        for(int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                Point p = findIntersectionPoint(line[i], line[j]);
                if (p == null) continue;
                points.add(p);
                maxX = Math.max(maxX, p.x);
                maxY = Math.max(maxY, p.y);
                minX = Math.min(minX, p.x);
                minY = Math.min(minY, p.y);
            }
        }
        String[] coordinates = new String[(int) (maxY - minY + 1)];
        Arrays.fill(coordinates, ".".repeat((int) Math.max(0, maxX - minX + 1)));
        for(Point p : points) {
            String row = coordinates[(int) -(p.y - maxY)];
            coordinates[(int) -(p.y - maxY)] = row.substring(0, (int) (p.x - minX)) + "*" + row.substring((int) (p.x - minX + 1));
        }
        return coordinates;
    }

    private Point findIntersectionPoint (int[] line1, int[] line2) {
        double A = line1[0]; double B = line1[1]; double E = line1[2]; double C = line2[0]; double D = line2[1]; double F = line2[2];
        double x = (B*F - E*D) / (A*D - B*C);
        double y = (E*C - A*F) / (A*D - B*C);

        if(A*D - B*C == 0 || !isInteger(x, y)) {
            return null;
        }
        return new Point((long) x, (long) y);
    }

    private boolean isInteger (double x, double y) {
        return x == (long) x && y == (long) y;
    }

    static class Point {
        long x;
        long y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}