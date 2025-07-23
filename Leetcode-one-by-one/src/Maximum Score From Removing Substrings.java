/*class Solution {
    public int maximumGain(String s, int x, int y) {
        Stack<Character> st = new Stack<>();
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if (!st.isEmpty()) {
                if (ch == 'b' && st.peek() == 'a') {
                    res += x;
                    st.pop();
                    continue;
                } else if (ch == 'a' && st.peek() == 'b') {
                    res += y;
                    st.pop();
                    continue;
                }
            }
            st.push(ch);
        }
        return res;
    }
}
*/
class Solution {
    public int maximumGain(String s, int x, int y) {
        char first = x > y ? 'a' : 'b';
        char second = x > y ? 'b' : 'a';
        int highGain = Math.max(x, y);
        int lowGain = Math.min(x, y);

        int total = 0;
        Stack<Character> stack = new Stack<>();

        // First pass: remove all high-gain pairs ("ab" or "ba")
        StringBuilder remaining = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == first && c == second) {
                stack.pop();
                total += highGain;
            } else {
                stack.push(c);
            }
        }

        // Build the remaining string
        while (!stack.isEmpty()) remaining.append(stack.pop());
        remaining.reverse();

        // Second pass: remove all low-gain pairs ("ba" or "ab")
        stack.clear();
        for (int i = 0; i < remaining.length(); i++) {
            char c = remaining.charAt(i);
            if (!stack.isEmpty() && stack.peek() == second && c == first) {
                stack.pop();
                total += lowGain;
            } else {
                stack.push(c);
            }
        }

        return total;
    }
}
