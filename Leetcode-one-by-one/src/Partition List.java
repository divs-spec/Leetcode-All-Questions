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
    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode(0);
        ListNode tempL = left;
        ListNode right = new ListNode(0);
        ListNode tempR = right;
        while(head!=null){
            if(head.val<x){
                tempL.next = head;
                tempL = tempL.next;
                head = head.next;
            } else{
                tempR.next = head;
                tempR = tempR.next;
                head = head.next;
            }
        }
        tempL.next = right.next;
        tempR.next = null;
        return left.next;

        
    }
}
