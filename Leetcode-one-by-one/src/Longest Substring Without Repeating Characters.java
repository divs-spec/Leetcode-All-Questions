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
