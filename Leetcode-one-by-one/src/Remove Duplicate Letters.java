import java.util.*;

public class Solution {
    public String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26]; // last occurrence of each char
        boolean[] visited = new boolean[26]; // if char is in stack
        Deque<Character> stack = new ArrayDeque<>();

        // Step 1: Record last index of each character
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        // Step 2: Build the result using a stack
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (visited[ch - 'a']) continue;

            // Maintain lexicographical order
            while (!stack.isEmpty() &&
                   ch < stack.peek() &&
                   lastIndex[stack.peek() - 'a'] > i) {
                char removed = stack.pop();
                visited[removed - 'a'] = false;
            }

            stack.push(ch);
            visited[ch - 'a'] = true;
        }

        // Build the final result from stack
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }

        return sb.reverse().toString(); // stack was built in reverse
    }
}
