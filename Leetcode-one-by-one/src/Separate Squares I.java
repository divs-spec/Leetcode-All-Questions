class Solution {
    public double separateSquares(int[][] squares) {
        double totalArea = 0;
        double low = Double.MAX_VALUE;
        double high = Double.MIN_VALUE;

        for (int[] sq : squares) {
            double y = sq[1];
            double l = sq[2];
            totalArea += l * l;
            low = Math.min(low, y);
            high = Math.max(high, y + l);
        }

        double target = totalArea / 2.0;

        for (int iter = 0; iter < 60; iter++) { // sufficient for 1e-5
            double mid = (low + high) / 2.0;
            double below = areaBelow(mid, squares);

            if (below < target) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private double areaBelow(double h, int[][] squares) {
        double area = 0.0;

        for (int[] sq : squares) {
            double y = sq[1];
            double l = sq[2];

            if (h <= y) {
                continue;
            } else if (h >= y + l) {
                area += l * l;
            } else {
                area += (h - y) * l;
            }
        }

        return area;
    }
}
