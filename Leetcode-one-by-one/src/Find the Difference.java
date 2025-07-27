/*class Solution {
    public char findTheDifference(String s, String t) {
        int xor = 0;
        for (char chr : s.toCharArray()) {
            xor ^= chr;
        }
        for (char chr : t.toCharArray()) {
            xor ^= chr;
        }
        return (char) xor;
    }
}*/
class Solution {
    public char findTheDifference(String s, String t) {
        char c = 0;
        for(char cs : s.toCharArray()) c ^= cs;
        for(char ct : t.toCharArray()) c ^= ct;
        return c;
    }
}
