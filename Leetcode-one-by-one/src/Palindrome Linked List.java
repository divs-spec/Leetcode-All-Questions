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
    public boolean isPalindrome(ListNode head) {
        ListNode t = head;
        Stack<Integer> st = new Stack<>();
        while(t != null){
            st.push(t.val);
            t = t.next;
        }
        while(head != null){
            if(head.val != st.pop())
            return false;
            head = head.next;
        }
        return true;
    }
}
