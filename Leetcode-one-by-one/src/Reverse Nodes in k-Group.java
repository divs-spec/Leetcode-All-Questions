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
    public ListNode reverseKGroup(ListNode head, int k) {
       
        ListNode dummy = new ListNode(-1);
        ListNode slow = head;
        ListNode fast = dummy;
        dummy.next = slow;
        ListNode ans = dummy;
        
        int count =0;
        while(fast != null && fast.next!= null ){ 
            if(fast.next != null){
                count++;
                fast = fast.next;
                
            }
            if(count==k){
                ListNode nextGroup = fast.next; 
                fast.next = null; 
                dummy.next = reverse(slow,k);
                dummy = slow;
                dummy.next = nextGroup;
                slow =nextGroup;
                count = 0; 
                
                fast = dummy;
            }
        }
        return ans.next;
    }

    static ListNode reverse(ListNode shead,int k){
        int count = 0;
        ListNode prev = null;

        while(count < k ){
            ListNode temp = shead.next;
            shead.next = prev;
            prev =  shead;
            shead = temp; 
            count++;
        }
        return prev;
    }
}
