import java.util.*;

class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int x : nums) list.add(x);

        int ops = 0;

        while (!isNonDecreasing(list)) {
            int minSum = Integer.MAX_VALUE;
            int idx = -1;

            // find adjacent pair with minimum sum
            for (int i = 0; i + 1 < list.size(); i++) {
                int sum = list.get(i) + list.get(i + 1);
                if (sum < minSum) {
                    minSum = sum;
                    idx = i;
                }
            }

            // merge the pair
            int merged = list.get(idx) + list.get(idx + 1);
            list.remove(idx + 1);
            list.set(idx, merged);

            ops++;
        }

        return ops;
    }

    private boolean isNonDecreasing(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
