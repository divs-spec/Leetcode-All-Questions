class Solution {
    public boolean isValidSerialization(String preorder) {
        int[] index = new int[1];
        int n = preorder.length();
        boolean haveChilds = checkChilds(preorder, index);
        
        if(index[0] == n && haveChilds) {
            return true;
        } 
        return false;
    }

    private boolean checkChilds(String data, int[] index) {
        if(index[0] == data.length()) {
            return false;
        }
        Integer currVal = extract(data, index); 
        if(currVal == null) return true;

        // left 
        boolean left = checkChilds(data, index);
        if(!left) return false;

        // right 
        boolean right = checkChilds(data, index);
        return right;
    }

    private Integer extract(String preorder, int[] index) {
        int n = preorder.length();
        if(preorder.charAt(index[0]) == '#') {
            index[0]++;
            if(index[0] != n) {
                // skip comma
                index[0]++;
            }
            return null;
        }

        int number = 0;
        while(index[0] < n && preorder.charAt(index[0]) != ',') {
            number = number*10 + ((int)preorder.charAt(index[0])-'0');
            index[0]++;
        }
        if(index[0] != n) {
            // skipping comma 
            index[0]++;
        }
        return number;
    }
}
