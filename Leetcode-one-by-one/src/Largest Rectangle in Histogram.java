class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int length = heights.length;
        int area = 0;

        for (int i = 0; i < length; i++) {
            // Process stack while current bar is smaller than top of stack
            while (!st.empty() && heights[i] < heights[st.peek()]) {
                int bar = st.pop(); // Index of the bar
                int pse = st.empty() ? -1 : st.peek(); // Previous smaller element index
                int nse = i; // Current index is next smaller element
                // Width = nse - pse - 1
                area = Math.max(area, heights[bar] * (nse - pse - 1));
            }
            st.push(i);
        }

        // Process remaining bars in stack
        while (!st.empty()) {
            int bar = st.pop();
            int pse = st.empty() ? -1 : st.peek();
            int nse = length;
            area = Math.max(area, heights[bar] * (nse - pse - 1));
        }

        return area;
    }
}
