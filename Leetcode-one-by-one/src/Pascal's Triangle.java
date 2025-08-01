/*class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(List.of(1));

        for (int i = 0; i < numRows - 1; i++) {
            List<Integer> dummyRow = new ArrayList<>();
            dummyRow.add(0);
            dummyRow.addAll(res.get(res.size() - 1));
            dummyRow.add(0);
            List<Integer> row = new ArrayList<>();

            for (int j = 0; j < dummyRow.size() - 1; j++) {
                row.add(dummyRow.get(j) + dummyRow.get(j + 1));
            }

            res.add(row);
        }

        return res;        
    }
}
*/
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ls = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for(int i = 0; i < numRows; i++){
            if(i == 0){
                temp.add(1);
                ls.add(temp);
            }
            else{
                temp = func(temp);
                ls.add(temp);
            }
        }
        return ls;
    }
    public List<Integer> func(List<Integer> t){
        List<Integer> ls = new ArrayList<>();
        int x = 0;
        for(Integer e : t){
            ls.add(x+e);
            x = e;
        }
        ls.add(1);
        return ls;
    }
}
