/*class Solution {
    public int lengthLongestPath(String input) {
        int longest = 0;
        Map<Integer, Integer> pathMap = new HashMap<>();
        pathMap.put(0, 0);

        String[] lines = input.split("\n");

        for (String line : lines) {
            String name = line.replaceAll("\t", "");
            int depth = line.length() - name.length();

            if (name.contains(".")) {
                // It's a file
                longest = Math.max(longest, pathMap.get(depth) + name.length());
            } else {
                // It's a directory
                pathMap.put(depth + 1, pathMap.get(depth) + name.length() + 1);
            }
        }

        return longest;
    }
}
*/
class Solution {
    public int lengthLongestPath(String input) {
        int maxlength = 0;
        Stack<Integer> pathlengths = new Stack<>();
        String[] lines = input.split("\n");
        
        for (String line : lines) {
            int depth = 0;

            while (line.charAt(depth) == '\t') {
                depth++;
            }

            while (pathlengths.size() > depth) {
                pathlengths.pop();
            }

            String name = line.substring(depth);
            int length = (pathlengths.isEmpty() ? 0 : pathlengths.peek()) + name.length() + (pathlengths.isEmpty() ? 0 : 1);

            if (name.contains(".")) {
                maxlength = Math.max(maxlength, length);
            } else {
                pathlengths.push(length);
            }
        }
        return maxlength;
    }
}
