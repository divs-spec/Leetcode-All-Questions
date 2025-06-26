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
    public ListNode removeNthFromEnd(ListNode head, int n) {
       ListNode temp=head;
      // int size=ListNode.size();
      int size=0;
      while(temp!=null){
        size++;
        temp=temp.next;
      }
       if(size==n)return head.next;
       //int count=1;
        temp=head;
       while(temp!=null&&temp.next!=null  ){
        size--;
        if(size==n){
            temp.next=temp.next.next;
            return head;
       }
       temp=temp.next;
    }
    return head;
}}
