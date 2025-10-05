//Approach no 1
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows[currRow].append(c);

            // Change direction when reaching top or bottom row
            if (currRow == 0 || currRow == numRows - 1) goingDown = !goingDown;

            currRow += goingDown ? 1 : -1;
        }

        // Combine all rows
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) result.append(row);

        return result.toString();
    }
}


//Approach no 2

class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        StringBuilder result = new StringBuilder();
        int cycleLen = 2 * numRows - 2; // One full zigzag cycle

        for (int row = 0; row < numRows; row++) {
            for (int j = row; j < s.length(); j += cycleLen) {
                result.append(s.charAt(j)); // Vertical column char

                // For middle rows, add the diagonal element
                int diag = j + cycleLen - 2 * row;
                if (row != 0 && row != numRows - 1 && diag < s.length()) {
                    result.append(s.charAt(diag));
                }
            }
        }

        return result.toString();
    }
}
