class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        double maxArea = 0, maxDiagonal = 0;
        for(int i = 0; i < dimensions.length; i++){
            int w = dimensions[i][0];
            int h = dimensions[i][1];
            double diagonal = Math.sqrt(w * w + h * h);
            int area = w * h;

            if (diagonal > maxDiagonal) {
                maxDiagonal = diagonal;
                maxArea = area;
            } else if (diagonal == maxDiagonal) {
                maxArea = Math.max(maxArea, area);
            }
        }
        return (int)maxArea;
    }
}
