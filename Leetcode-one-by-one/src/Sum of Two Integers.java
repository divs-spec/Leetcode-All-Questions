class Solution {
    public int getSum(int a, int b) {
        if(Math.abs(a)<Math.abs(b)){
            if(a>0){
                while(a>0){
                    b++;
                    a--;
                }
            }else{
                while(a<0){
                    b--;
                    a++;
                }
            }
            return b;
        }else{
            if(b>0){
                while(b>0){
                    a++;
                    b--;
                }
            }else{
                while(b<0){
                    a--;
                    b++;
                }
            }
            return a;
        }
        
    }
}
