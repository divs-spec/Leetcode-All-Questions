class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLen = 0;
        int left = 0;

        // store last seen index of each character
        int[] lastSeen = new int[256];  
        // initialize all to -1
        java.util.Arrays.fill(lastSeen, -1);

        for (int right = 0; right < n; right++) {
            char ch = s.charAt(right);

            // if character was seen before and is inside the current window
            if (lastSeen[ch] >= left) {
                left = lastSeen[ch] + 1;
            }

            lastSeen[ch] = right;  // update last seen index
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}



class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> hm = new HashMap<>();
        int st = 0, maxlen = 0;
        for(int i = 0; i < s.length(); i++){
            if(hm.containsKey(s.charAt(i)) && hm.get(s.charAt(i)) >= st)
            st = hm.get(s.charAt(i)) + 1;
            hm.put(s.charAt(i),i);
            maxlen = Math.max(maxlen, i - st + 1);
        }
        return maxlen;
    }
}
