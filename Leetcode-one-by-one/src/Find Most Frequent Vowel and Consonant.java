class Solution {
    public int maxFreqSum(String s) {
        HashMap<Character,Integer> hm = new HashMap<>();
        int c = 0, v = 0;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(hm.containsKey(ch))
            {
                hm.put(ch,hm.get(ch) + 1);
            }
            else{
                hm.put(ch,1);
            }
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
            v = Math.max(v,hm.get(ch));
            else
            c = Math.max(c,hm.get(ch));
        }
        return c + v;
    }
}
