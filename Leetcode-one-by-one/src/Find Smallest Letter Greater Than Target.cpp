class Solution {
public:
    char nextGreatestLetter(vector<char>& letters, char target) {
        int left = 0, right = letters.size() - 1;
        char result = letters[0]; // default wrap-around

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (letters[mid] > target) {
                result = letters[mid];
                right = mid - 1;   // try to find smaller valid char
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
};
