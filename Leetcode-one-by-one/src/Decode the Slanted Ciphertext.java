class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if(rows == 1)
        {
            return encodedText;
        }
        int n = encodedText.length();
        n = n/rows;
        int idx = 0;
        Character[][] mat = new Character[rows][n];
        for(int i =0; i < rows; i++){
            for(int j = 0; j < n; j++){
                mat[i][j] = encodedText.charAt(idx++);
            }
        }
        StringBuilder st = new StringBuilder();
        for(int i = 0 ; i < n; i++){
            int r = 0, c = i;
            while(r < rows && c < n){ 
                st.append(mat[r][c]);
                ++r;
                ++c;
            }
        }
        while (st.length() > 0 && st.charAt(st.length() - 1) == ' ') {
            st.deleteCharAt(st.length() - 1);
        }

        return st.toString();
    }
}
