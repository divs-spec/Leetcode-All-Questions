class Solution {
    public boolean isSubsequence(String s, String t) {
        int in=0;
        boolean d=false;
        for(int i=0;i<s.length();i++){
            d=false;
            for(int j=in;j<t.length();j++){
                if(s.charAt(i)==t.charAt(j)){
                    in=j+1;
                    d=true;
                    break;
                }
            }
            if(!d){
                return false;
            }
        }
        return true;
    }
}
