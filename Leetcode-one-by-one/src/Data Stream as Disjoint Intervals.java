// APPROACH NO 1 :
class SummaryRanges {
  public void addNum(int val) {
    if (map.containsKey(val))
      return;

    final Integer lo = map.lowerKey(val);  // Maximum in map < key
    final Integer hi = map.higherKey(val); // Minimum in map > key

    // {lo, map.get(lo)[1]} + val + {hi, map.get(hi)[1]} = {lo, map.get(hi)[1]}
    if (lo != null && hi != null && map.get(lo)[1] + 1 == val && val + 1 == hi) {
      map.get(lo)[1] = map.get(hi)[1];
      map.remove(hi);
      // {lo, map.get(lo)[1]} + val = {lo, val}
      // (prevent adding duplicate entry by using '>=' instead of '==')
    } else if (lo != null && map.get(lo)[1] + 1 >= val) {
      map.get(lo)[1] = Math.max(map.get(lo)[1], val);
      // Val + {hi, map.get(hi)[1]} = {val, map.get(hi)[1]}
    } else if (hi != null && val + 1 == hi) {
      map.put(val, new int[] {val, map.get(hi)[1]});
      map.remove(hi);
    } else {
      map.put(val, new int[] {val, val});
    }
  }

  public int[][] getIntervals() {
    List<int[]> intervals = new ArrayList<>(map.values());
    return intervals.toArray(new int[intervals.size()][]);
  }

  // {start: {start, end}}
  private TreeMap<Integer, int[]> map = new TreeMap<>();
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */

//APPROACH NO 2 :

class SummaryRanges {
  private TreeMap<Integer, Integer> intervals;

    public SummaryRanges() {
        intervals = new TreeMap<>(); 
    }
    
    public void addNum(int value) {
        final Map.Entry<Integer, Integer> minEntry = intervals.floorEntry(value); 
        int left = value, right = value;
        if (minEntry != null) {
            int rightEntry = minEntry.getValue();
            if (rightEntry >= value) {
                return;
            }
            if (rightEntry == value - 1) {
                left = minEntry.getKey();
            }
        }
        final Map.Entry<Integer, Integer> maxEntry = intervals.higherEntry(value); 
        if (maxEntry != null && maxEntry.getKey() == value + 1) {
            right = maxEntry.getValue();
            intervals.remove(value + 1);
        }
        intervals.put(left, right);
    }
    
    public int[][] getIntervals() {
        final int[][] answer = new int[intervals.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : intervals.entrySet()) {
            answer[i][0] = entry.getKey();
            answer[i][1] = entry.getValue();
            i++;
        }
        return answer; 
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */
