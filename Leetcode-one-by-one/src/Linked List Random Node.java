/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    ListNode h ;
    int length;
    public Solution(ListNode head) {
        h = head;
        ListNode temp = head;
        while(temp != null){
            length++;
            temp = temp.next;
        }
    }
    
    public int getRandom() {
        int r = (int)(Math.random()*(length));
        int idx = 0;
        ListNode temp = h;
        while(idx != r && (temp.next != null)){
            idx++;
            temp = temp.next;
        }
        return temp.val;
    }
}



/*
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
import java.util.*;
class Solution {
    private List<Integer> arr;
    private Random rand;
    public Solution(ListNode head) {
        arr = new ArrayList<>();
        rand = new Random();
        ListNode temp = head;
        while (temp != null) {
            arr.add(temp.val);
            temp = temp.next;
        }
    }
    public int getRandom() {
        int r_index = rand.nextInt(arr.size());
        return arr.get(r_index);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
*/

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
