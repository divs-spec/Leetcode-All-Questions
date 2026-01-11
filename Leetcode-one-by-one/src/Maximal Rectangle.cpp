#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    int maximalRectangle(vector<vector<char>>& matrix) {
        if (matrix.empty() || matrix[0].empty()) {
            return 0;
        }

        int rows = matrix.size();
        int cols = matrix[0].size();
        vector<int> heights(cols, 0);
        int maxArea = 0;

        for (int r = 0; r < rows; r++) {
            // Update histogram heights
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == '1') {
                    heights[c]++;
                } else {
                    heights[c] = 0;
                }
            }
            // Compute largest rectangle in histogram
            maxArea = max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

private:
    int largestRectangleArea(const vector<int>& heights) {
        int n = heights.size();
        vector<int> stack;
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.empty() && currentHeight < heights[stack.back()]) {
                int h = heights[stack.back()];
                stack.pop_back();
                int width = stack.empty() ? i : i - stack.back() - 1;
                maxArea = max(maxArea, h * width);
            }
            stack.push_back(i);
        }

        return maxArea;
    }
};
