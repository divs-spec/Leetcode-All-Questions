//Approach no 1: TLE
class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[] duration = new int[n];
        for (int i = 0; i < n; i++) {
            duration[i] = endTime[i] - startTime[i];
        }

        // Try rescheduling every possible prefix of k meetings
        // Greedy simulate by choosing the best k meetings to move
        int maxFree = 0;

        // DP-like greedy try of moving the "best" k meetings
        PriorityQueue<Gap> gaps = new PriorityQueue<>((a, b) -> b.len - a.len); // max-heap by gap size

        // Try all reschedule combinations greedily from back to front
        for (int mask = 0; mask < (1 << n); mask++) {
            if (Integer.bitCount(mask) > k) continue;

            int currTime = 0;
            boolean valid = true;
            int[] ns = new int[n];
            int[] ne = new int[n];

            for (int i = 0; i < n; i++) {
                int d = duration[i];

                if (((mask >> i) & 1) == 1) {
                    int newStart = currTime;
                    int newEnd = newStart + d;

                    if (newEnd > endTime[i]) {
                        valid = false;
                        break;
                    }

                    ns[i] = newStart;
                    ne[i] = newEnd;
                } else {
                    ns[i] = startTime[i];
                    ne[i] = endTime[i];

                    if (i > 0 && ne[i - 1] > ns[i]) {
                        valid = false;
                        break;
                    }
                }
                currTime = ne[i];
            }

            if (!valid) continue;

            // Calculate max free gap
            int free = ns[0]; // before first
            for (int i = 1; i < n; i++) {
                free = Math.max(free, ns[i] - ne[i - 1]);
            }
            free = Math.max(free, eventTime - ne[n - 1]);
            maxFree = Math.max(maxFree, free);
        }

        return maxFree;
    }

    static class Gap {
        int start, end, len;
        Gap(int s, int e) {
            start = s;
            end = e;
            len = e - s;
        }
    }
}


//Approach no 2: NO TLE
class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int count = startTime.length;
        int[] prefixSum = new int[count + 1];
        int maxFree = 0;

        for (int i = 0; i < count; i++) {
            prefixSum[i + 1] = prefixSum[i] + endTime[i] - startTime[i];
        }

        for (int i = k - 1; i < count; i++) {
            int occupied = prefixSum[i + 1] - prefixSum[i - k + 1];
            int windowEnd = (i == count - 1) ? eventTime : startTime[i + 1];
            int windowStart = (i == k - 1) ? 0 : endTime[i - k];
            int freeTime = windowEnd - windowStart - occupied;
            maxFree = Math.max(maxFree, freeTime);
        }

        return maxFree;
    }
}
