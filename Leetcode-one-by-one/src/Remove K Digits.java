class Solution {
    public String removeKdigits(String num, int k) {  // lowercase 'd'
        if (k == num.length()) return "0"; // removing all digits
        
        StringBuilder stack = new StringBuilder();
        
        for (char digit : num.toCharArray()) {
            while (k > 0 && stack.length() > 0 && stack.charAt(stack.length() - 1) > digit) {
                stack.deleteCharAt(stack.length() - 1);
                k--;
            }
            stack.append(digit);
        }
        
        // Remove remaining k digits from the end
        stack.setLength(stack.length() - k);
        
        // Remove leading zeros
        int idx = 0;
        while (idx < stack.length() && stack.charAt(idx) == '0') idx++;
        
        String result = (idx == stack.length()) ? "0" : stack.substring(idx);
        return result;
    }
}
