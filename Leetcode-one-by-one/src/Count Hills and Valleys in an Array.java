/*
Approach no 1:

class Solution {
    public int countHillValley(int[] nums) {

        int n = nums.length;
        int[] arr = new int[n];
        int res = 0;
        int i = 0;
        int j = 0;
        while(i < n){
            while(i < n && nums[i] == nums[j]){
                i++;
            }
            if(i == n){
                break;
            }
            if(nums[j] < nums[i]){
                if(arr[j] == -1){
                    res++;
                }
                arr[i] = 1;
            }else if(nums[j] > nums[i]){
                if(arr[j] == 1){
                    res++;
                }
                arr[i] = -1;
            }
            j = i;
            i++;
        }
        return res;
    }
}
*/

/*
Approach no 2:

class Solution {
    public int countHillValley(int[] nums) {
         int count = 0, n = nums.length;

        for (int i = 1; i < n - 1; i++) {
              if (nums[i] == nums[i - 1]) continue;
            // Find previous different value
            int left = i - 1;
            while (left >= 0 && nums[left] == nums[i]) {
                left--;
            }
            int right = i + 1;
            while (right < n && nums[right] == nums[i]) {
                right++;
            }
            if (left >= 0 && right < n) {
                if (nums[i] > nums[left] && nums[i] > nums[right]) {
                    count++; // hill
                } else if (nums[i] < nums[left] && nums[i] < nums[right]) {
                    count++; // valley
                }
            }
        }

        return count;
    }
}
*/

/*
Approach no 3: 

class Solution {
    public int countHillValley(int[] nums) {
        int count = 0;
        // Remove adjacent duplicates
        List<Integer> clean = new ArrayList<>();
        clean.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                clean.add(nums[i]);
            }
        }

        // Count hills and valleys
        for (int i = 1; i < clean.size() - 1; i++) {
            if (clean.get(i) > clean.get(i - 1) && clean.get(i) > clean.get(i + 1)) {
                count++; // hill
            } else if (clean.get(i) < clean.get(i - 1) && clean.get(i) < clean.get(i + 1)) {
                count++; // valley
            }
        }

        return count;
    }
}

*/


// Approach no 4:

class Solution {
    public int countHillValley(int[] nums) {
        int left=nums[0];
        int count=0;
        for(int i=0;i<nums.length-1;i++){
            if((left<nums[i] && nums[i]>nums[i+1]) || (left>nums[i] && nums[i]<nums[i+1])){
                count++;
                left=nums[i];
            }
        }
        return count;
    }
}
