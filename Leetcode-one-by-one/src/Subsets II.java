class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<>();
        findSubsets(0, nums, new ArrayList<>(), resultList);
        return resultList;
    }
    public void findSubsets(int index, int[] arr, List<Integer> ds, List<List<Integer>> ansList)
    {
        ansList.add(new ArrayList<>(ds));
        for(int i = index; i < arr.length; i++)
        {
            if(i != index && arr[i] == arr[i-1])
            {
                continue;  // The continue is used to avoid generating the same subset multiple times, not to prevent duplicates from appearing in subsets
            }
            ds.add(arr[i]);
            findSubsets(i+1, arr, ds, ansList);
            ds.remove(ds.size()-1);
        }
    }
}


//  T(n) = O(n!*n)
//  S(n) = O(n)
