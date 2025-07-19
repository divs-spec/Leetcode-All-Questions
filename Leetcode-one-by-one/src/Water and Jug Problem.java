class Solution {
    public boolean canMeasureWater(int x, int y, int target) {
        return (target % gcd(x, y) == 0) && (x + y >= target);
    }

    // Helper method to compute GCD (Greatest Common Divisor)
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
