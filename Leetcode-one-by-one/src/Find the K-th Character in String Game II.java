https://leetcode.com/problems/find-the-k-th-character-in-string-game-ii/solutions/6920348/full-explained-java-solution-beginner-fr-qr8r

// APPROACH NO 1:
class Solution {
    public char kthCharacter(long k, int[] operations) {
        int n = operations.length;
        List<Long> lengths = new ArrayList<>();
        lengths.add(1L); // Initially word = "a"

        // Step 1: Compute lengths of word after each operation
        for (int op : operations) {
            long prevLen = lengths.get(lengths.size() - 1);
            long newLen = Math.min(prevLen * 2, k); // Cap at k
            lengths.add(newLen);
            if (newLen >= k) break;
        }

        int idx = lengths.size() - 1;
        int incCount = 0;

        // Step 2: Trace back to original character at position k
        while (idx > 0) {
            int op = operations[idx - 1];
            long prevLen = lengths.get(idx - 1);

            if (op == 0) {
                if (k > prevLen) {
                    k -= prevLen; // Came from second half
                }
            } else { // op == 1
                if (k > prevLen) {
                    k -= prevLen; // Came from incremented half
                    incCount++;
                }
            }

            idx--;
        }

        // Final character was originally 'a' with incCount increments
        return (char) ((('a' - 'a' + incCount) % 26) + 'a');
    }
}

// APPROACH NO 2: ( OPTIMIZED SOLUTION)
public class Solution {
    public char kthCharacter(long k, int[] operations) {
        int n = operations.length;
        long[] lengths = new long[n + 1];
        lengths[0] = 1; // word = "a"

        int lenIndex = 1;
        for (int op : operations) {
            long prev = lengths[lenIndex - 1];
            lengths[lenIndex] = Math.min(prev * 2, k); // Cap at k
            lenIndex++;
            if (lengths[lenIndex - 1] >= k) break;
        }

        int incCount = 0;
        int idx = lenIndex - 1;

        // Trace backward to original character at position k
        while (idx > 0) {
            long prevLen = lengths[idx - 1];
            int op = operations[idx - 1];

            if (op == 0) {
                if (k > prevLen) k -= prevLen; // second half
            } else {
                if (k > prevLen) {
                    k -= prevLen;
                    incCount++;
                }
            }
            idx--;
        }

        // Compute final character starting from 'a' + incCount
        return (char) ((incCount % 26) + 'a');
    }
}
