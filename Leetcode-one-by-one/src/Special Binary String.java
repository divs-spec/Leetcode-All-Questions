import java.util.*;

class Solution {
    public String makeLargestSpecial(String s) {
        List<String> parts = new ArrayList<>();
        int balance = 0;
        int last = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') balance++;
            else balance--;

            // Found a primitive special substring
            if (balance == 0) {
                // Recursively process the inner substring
                String inner = makeLargestSpecial(s.substring(last + 1, i));
                parts.add("1" + inner + "0");
                last = i + 1;
            }
        }

        // Sort descending for lexicographically largest result
        Collections.sort(parts, Collections.reverseOrder());

        // Concatenate
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            sb.append(part);
        }

        return sb.toString();
    }
}
