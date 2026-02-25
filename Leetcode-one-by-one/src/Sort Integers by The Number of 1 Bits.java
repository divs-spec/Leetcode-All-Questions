import java.util.*;

class Solution {
    public int[] sortByBits(int[] arr) {
        Integer[] nums = Arrays.stream(arr).boxed().toArray(Integer[]::new);

        Arrays.sort(nums, (a, b) -> {
            int bitsA = Integer.bitCount(a);
            int bitsB = Integer.bitCount(b);
            if (bitsA != bitsB) {
                return bitsA - bitsB;
            }
            return a - b;
        });

        return Arrays.stream(nums).mapToInt(Integer::intValue).toArray();
    }
}
