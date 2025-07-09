class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> out_list = new ArrayList<>();
        for(int i=0;i<strs.length;i++){
            if(strs[i]!="-1"){
            String s1 = strs[i];
            int[] freq1 = new int[26];
            List<String> in_list = new ArrayList<>();
            in_list.add(s1);
            for(int i1 = 0;i1<s1.length();i1++){
                int val1 = s1.charAt(i1) - 'a';
                freq1[val1]++;
            }
            for(int j = i+1;j<strs.length;j++){
                if(strs[j] != "-1"){
                String s2 = strs[j];
                if(s1.length()!=s2.length()){
                    continue;
                }
                int[] freq2 = new int[26];
                for(int j1 = 0;j1<s2.length();j1++){
                int val2 = s2.charAt(j1) - 'a';
                freq2[val2]++;
                }
                int flag = 1;
                for(int k = 0;k<freq1.length;k++){
                    if(freq1[k] != freq2[k]){
                        flag =0;
                    }
                }
                if(flag == 1){
                    in_list.add(s2);
                    strs[j] = "-1";
                }
                //out_list.add(in_list);
            }
            }
            out_list.add(in_list);
            }
        }
        return out_list;
    }
}
