/*import java.util.*;

class Solution {
    public double soupServings(int n) {
        if (n >= 5000) return 1.0; // Optimization for large n

        int units = (n + 24) / 25; // Convert to units of 25 mL
        Map<String, Double> memo = new HashMap<>();
        int[][] ops = {{4, 0}, {3, 1}, {2, 2}, {1, 3}};

        return dfs(units, units, memo, ops);
    }

    private double dfs(int a, int b, Map<String, Double> memo, int[][] ops) {
        if (a <= 0 && b <= 0) return 0.5;
        if (a <= 0) return 1.0;
        if (b <= 0) return 0.0;

        String key = a + "," + b;
        if (memo.containsKey(key)) return memo.get(key);

        double prob = 0;
        for (int[] op : ops) {
            prob += dfs(a - op[0], b - op[1], memo, ops);
        }
        prob *= 0.25;

        memo.put(key, prob);
        return prob;
    }
}
*/

class Solution {
    private double[][] memo;
    private final int[][] ops = {{4, 0}, {3, 1}, {2, 2}, {1, 3}};

    public double soupServings(int n) {
        if (n >= 5000) return 1.0; // Early return for large n
        
        int units = (n + 24) / 25;
        memo = new double[units + 1][units + 1]; // initialized to 0.0
        return dfs(units, units);
    }

    private double dfs(int a, int b) {
        if (a <= 0 && b <= 0) return 0.5;
        if (a <= 0) return 1.0;
        if (b <= 0) return 0.0;

        if (memo[a][b] > 0) return memo[a][b]; // already computed

        double prob = 0;
        for (int[] op : ops) {
            prob += dfs(a - op[0], b - op[1]);
        }
        prob *= 0.25;

        return memo[a][b] = prob;
    }
}
