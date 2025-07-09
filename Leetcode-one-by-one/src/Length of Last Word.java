class Solution {
    public int lengthOfLastWord(String s) {
        StringBuilder sb = new StringBuilder(s);
        int len = sb.length();

        // Trimming the string to remove whitespaces from end
        while(sb.charAt(len - 1) == ' '){
            sb.deleteCharAt(len - 1);
            len--;
        }

        // Running for llop from end, if whitespace is found then return length of characrter before space.
        for(int i = len-1; i >= 0 ; i--){
            if(sb.charAt(i) == ' '){
                return len-1-i;
            }
        }

        // If no whitespace is found that means either string is empty or has no spaces
        // In that case returning length of string
        return len;
    }
}
