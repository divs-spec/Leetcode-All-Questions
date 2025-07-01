// APPROACH NO 1:
/*
✅ Efficient Approach: Modified Merge Sort (O(n log n))
This is the optimal solution for this problem.

🔥 Idea:
Pair each number with its index.

Use merge sort to count how many elements from the right half are placed before an element from the left half → these are the smaller elements after self.

Store counts in a result array based on original indices.

*/
class Solution {
    class Pair {
        int val, index;
        Pair(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Integer[] result = new Integer[n];
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(nums[i], i);
            result[i] = 0;
        }

        mergeSort(pairs, 0, n - 1, result);
        return Arrays.asList(result);
    }

    private void mergeSort(Pair[] pairs, int left, int right, Integer[] result) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSort(pairs, left, mid, result);
        mergeSort(pairs, mid + 1, right, result);
        merge(pairs, left, mid, right, result);
    }

    private void merge(Pair[] pairs, int left, int mid, int right, Integer[] result) {
        List<Pair> temp = new ArrayList<>();
        int i = left, j = mid + 1;
        int rightCount = 0;

        while (i <= mid && j <= right) {
            if (pairs[i].val <= pairs[j].val) {
                result[pairs[i].index] += rightCount;
                temp.add(pairs[i++]);
            } else {
                temp.add(pairs[j++]);
                rightCount++;
            }
        }

        while (i <= mid) {
            result[pairs[i].index] += rightCount;
            temp.add(pairs[i++]);
        }

        while (j <= right) {
            temp.add(pairs[j++]);
        }

        for (int k = left; k <= right; k++) {
            pairs[k] = temp.get(k - left);
        }
    }
}

// APPROACH NO 2:
/*
❌ Why O(n log n) is the Lower Bound
This problem requires computing comparisons between elements to the right of each index, which means:

At a minimum, you need to look at every element and consider its relationship with others → that’s Ω(n log n) for sorting-based counting.

Any approach that gives accurate counts must handle these comparisons efficiently — which is exactly what Merge Sort, Binary Indexed Tree (Fenwick Tree), or Segment Tree do.

✅ Theoretical Best Time Complexity
Merge Sort-based counting: O(n log n)

Fenwick Tree / Binary Indexed Tree (BIT): O(n log m)
(where m = number of unique values, using coordinate compression)

Balanced BST (TreeMap): O(n log n) — slower due to overhead

✅ Fastest Practical Java Solution (Using BIT)
Here’s a faster version than Merge Sort, especially when numbers are in a small range or have duplicates.

🔧 Steps:
Coordinate compression (map values to 1..m)

Use a Fenwick Tree to maintain prefix sums of counts

Traverse nums[] from right to left and use query() to count smaller elements

⚡ Java Code (BIT + Coordinate Compression)
  */

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        
        // Step 1: Coordinate compression
        TreeSet<Integer> sorted = new TreeSet<>();
        for (int num : nums) sorted.add(num);
        Map<Integer, Integer> indexMap = new HashMap<>();
        int id = 1;
        for (int num : sorted) indexMap.put(num, id++);

        // Step 2: Fenwick Tree
        int[] bit = new int[indexMap.size() + 2];

        // Step 3: Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            int mappedIndex = indexMap.get(nums[i]);
            result.add(query(bit, mappedIndex - 1));  // Count of smaller elements
            update(bit, mappedIndex);  // Add current number to BIT
        }

        Collections.reverse(result);
        return result;
    }

    private void update(int[] bit, int i) {
        while (i < bit.length) {
            bit[i]++;
            i += i & -i;
        }
    }

    private int query(int[] bit, int i) {
        int sum = 0;
        while (i > 0) {
            sum += bit[i];
            i -= i & -i;
        }
        return sum;
    }
}
