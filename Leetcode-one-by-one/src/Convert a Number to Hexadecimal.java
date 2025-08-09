/*class Solution {
    public String toHex(int num) {
        String str=Integer.toHexString(num);
        return str;
    }
}*/
class Solution {
    public String toHex(int num) {
        if (num == 0) return "0";

        long n = num; // use long to handle unsigned
        if (n < 0) {
            n += (1L << 32); // convert to unsigned equivalent
        }

        StringBuilder sb = new StringBuilder();
        char[] hexMap = "0123456789abcdef".toCharArray();

        while (n > 0) {
            int digit = (int)(n % 16);
            sb.append(hexMap[digit]);
            n /= 16;
        }

        return sb.reverse().toString();
    }
}
