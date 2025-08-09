import java.util.*;

class Solution {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        
        for (int stone : stones) {
            map.put(stone, new HashSet<>());
        }
        
        map.get(0).add(1); // first jump must be 1
        
        for (int stone : stones) {
            for (int step : map.get(stone)) {
                int reach = stone + step;
                if (reach == stones[n - 1]) return true;
                if (map.containsKey(reach)) {
                    if (step - 1 > 0) map.get(reach).add(step - 1);
                    map.get(reach).add(step);
                    map.get(reach).add(step + 1);
                }
            }
        }
        
        return false;
    }
}
