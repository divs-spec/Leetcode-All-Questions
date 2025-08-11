/*class Solution {
    public long specialPalindrome(long n) {
        
    }
}*/
/*public class Solution {
    
    public long specialPalindrome(long n) {
        long candidate = n + 1;
        while (true) {
            long palindrome = makePalindrome(candidate);
            if (palindrome > n && isSpecial(palindrome)) {
                return palindrome;
            }
            candidate = nextPalindromeStart(candidate);
        }
    }

    // Create a palindrome from the first half of candidate
    private long makePalindrome(long x) {
        String s = Long.toString(x);
        char[] arr = s.toCharArray();
        int len = arr.length;
        for (int i = 0; i < len / 2; i++) {
            arr[len - 1 - i] = arr[i];
        }
        return Long.parseLong(new String(arr));
    }

    // Get the next "half" to try
    private long nextPalindromeStart(long x) {
        String s = Long.toString(x);
        int len = s.length();
        int halfLen = (len + 1) / 2;
        long half = Long.parseLong(s.substring(0, halfLen)) + 1;
        String halfStr = Long.toString(half);
        if (halfStr.length() > halfLen) {
            // overflow in half -> next palindrome is 10...01
            StringBuilder sb = new StringBuilder();
            sb.append('1');
            for (int i = 0; i < len - 1; i++) sb.append('0');
            sb.append('1');
            return Long.parseLong(sb.toString());
        }
        // Reconstruct a palindrome from the incremented half
        StringBuilder sb = new StringBuilder(halfStr);
        if (len % 2 == 1) {
            sb.append(new StringBuilder(halfStr.substring(0, halfStr.length() - 1)).reverse());
        } else {
            sb.append(new StringBuilder(halfStr).reverse());
        }
        return Long.parseLong(sb.toString());
    }

    private boolean isSpecial(long x) {
        String s = Long.toString(x);
        int[] freq = new int[10];
        for (char c : s.toCharArray()) {
            freq[c - '0']++;
        }
        for (int d = 0; d <= 9; d++) {
            if (freq[d] != 0 && freq[d] != d) {
                return false;
            }
        }
        return true;
    }

    // Test
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.nextSpecialPalindrome(2));   // 22
        System.out.println(sol.nextSpecialPalindrome(22));  // 333
        System.out.println(sol.nextSpecialPalindrome(120)); // 222
    }
}*/
/*import java.util.*;

public class Solution {
    private List<Long> specialNumbers;

    public Solution() {
        specialNumbers = new ArrayList<>();
    }

    private boolean isPalindromePossible(int[] digitCounts) {
        int oddCountDigits = 0;
        for (int digit = 0; digit <= 9; digit++) {
            if (digitCounts[digit] % 2 != 0) oddCountDigits++;
        }
        return oddCountDigits <= 1;
    }

    private void buildSpecialNumbers() {
        specialNumbers.clear();
        // Try all subsets of digits 0-9 (0 handled carefully)
        int totalMasks = 1 << 10;
        for (int subsetMask = 1; subsetMask < totalMasks; subsetMask++) {
            int[] counts = new int[10];
            int lengthSum = 0;
            boolean isValid = true;

            for (int digit = 0; digit <= 9; digit++) {
                if ((subsetMask & (1 << digit)) != 0) {
                    counts[digit] = digit; // digit appears exactly digit times
                    lengthSum += digit;
                    if (lengthSum > 18) { // limit length to avoid overflow
                        isValid = false;
                        break;
                    }
                }
            }
            if (!isValid) continue;
            if (lengthSum == 0) continue;
            if (!isPalindromePossible(counts)) continue;

            // Construct smallest palindrome from counts
            StringBuilder halfBuilder = new StringBuilder();
            String middleChar = "";
            for (int digit = 0; digit <= 9; digit++) {
                if (counts[digit] % 2 != 0) {
                    middleChar = Character.toString((char) ('0' + digit));
                }
                for (int c = 0; c < counts[digit] / 2; c++) {
                    halfBuilder.append((char) ('0' + digit));
                }
            }

            // Sort half to get smallest palindrome half
            char[] halfChars = halfBuilder.toString().toCharArray();
            Arrays.sort(halfChars);

            // Avoid leading zero in half if possible
            if (halfChars.length > 0 && halfChars[0] == '0') {
                int nonZeroPos = -1;
                for (int i = 1; i < halfChars.length; i++) {
                    if (halfChars[i] != '0') {
                        nonZeroPos = i;
                        break;
                    }
                }
                if (nonZeroPos == -1) continue; // only zeros means invalid
                char temp = halfChars[0];
                halfChars[0] = halfChars[nonZeroPos];
                halfChars[nonZeroPos] = temp;
            }

            String halfStr = new String(halfChars);
            String reversedHalf = new StringBuilder(halfStr).reverse().toString();

            String palindromeStr = halfStr + middleChar + reversedHalf;
            long palindromeNum;
            try {
                palindromeNum = Long.parseLong(palindromeStr);
            } catch (NumberFormatException e) {
                // overflow or invalid number, skip
                continue;
            }

            specialNumbers.add(palindromeNum);
        }

        Collections.sort(specialNumbers);
    }

    public long specialPalindrome(long inputNum) {
        if (specialNumbers.isEmpty()) {
            buildSpecialNumbers();
        }
        int idx = Collections.binarySearch(specialNumbers, inputNum);
        if (idx < 0) idx = -(idx + 1);
        else idx++; // if found exact, take next bigger one
        return (idx < specialNumbers.size()) ? specialNumbers.get(idx) : -1;
    }
}*/
import java.util.*;

class Solution {
    static final long MAX = 10_000_000_000_000_000L; // 1e16
    static List<Long> specials = new ArrayList<>();
    static boolean generated = false;

    // Generate all special palindromes up to MAX efficiently
    static void generateSpecialPalindromes() {
        if (generated) return;
        generated = true;

        for (int mask = 1; mask < (1 << 9); mask++) { 
            int totalLen = 0;
            int oddCountDigits = 0;
            int centerDigit = -1;

            for (int d = 1; d <= 9; d++) {
                if ((mask & (1 << (d - 1))) != 0) {
                    totalLen += d;
                    if ((d & 1) == 1) {
                        oddCountDigits++;
                        centerDigit = d;
                    }
                }
            }
            if (totalLen == 0 || totalLen > 16) continue;
            if (oddCountDigits > 1) continue;

            int halfLen = 0;
            for (int d = 1; d <= 9; d++) {
                if ((mask & (1 << (d - 1))) != 0) {
                    halfLen += d / 2;
                }
            }

            char[] halfArr = new char[halfLen];
            int idx = 0;
            for (int d = 1; d <= 9; d++) {
                if ((mask & (1 << (d - 1))) != 0) {
                    int times = d / 2;
                    for (int t = 0; t < times; t++) {
                        halfArr[idx++] = (char) ('0' + d);
                    }
                }
            }

            if (halfLen == 0) {
                if (centerDigit != -1) {
                    long val = centerDigit;
                    if (val <= MAX) specials.add(val);
                }
                continue;
            }

            Arrays.sort(halfArr);
            do {
                String firstHalf = new String(halfArr);
                StringBuilder sb = new StringBuilder(firstHalf);
                String secondHalf = sb.reverse().toString();

                String palStr;
                if (centerDigit != -1) {
                    palStr = firstHalf + (char) ('0' + centerDigit) + secondHalf;
                } else {
                    palStr = firstHalf + secondHalf;
                }

                long val = Long.parseLong(palStr);
                if (val <= MAX) specials.add(val);

            } while (nextPermutation(halfArr));
        }

        Collections.sort(specials);
        List<Long> unique = new ArrayList<>(specials.size());
        long prev = -1L;
        for (long v : specials) {
            if (v != prev) {
                unique.add(v);
                prev = v;
            }
        }
        specials = unique;
    }

    
    static boolean nextPermutation(char[] a) {
        int i = a.length - 2;
        while (i >= 0 && a[i] >= a[i + 1]) i--;
        if (i < 0) return false;
        int j = a.length - 1;
        while (a[j] <= a[i]) j--;
        char tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        int l = i + 1, r = a.length - 1;
        while (l < r) {
            tmp = a[l];
            a[l] = a[r];
            a[r] = tmp;
            l++; r--;
        }
        return true;
    }

    
    public long specialPalindrome(long n) {
        generateSpecialPalindromes();
        int idx = Collections.binarySearch(specials, n + 1);
        if (idx < 0) idx = -idx - 1;
        return idx < specials.size() ? specials.get(idx) : -1L;
    }
}

