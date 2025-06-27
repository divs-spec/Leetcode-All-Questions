class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int k = lists.length;
        ArrayList<Integer> lst = new ArrayList<>();
        for(int i = 0; i < k; i++){
            ListNode curr = lists[i];
            while(curr != null){
                lst.add(curr.val);
                curr = curr.next;
            }
        }
        if(lst.isEmpty()) return null;
        Collections.sort(lst);
        ListNode head = new ListNode(lst.get(0));
        ListNode temp = head;
        for(int i = 1; i < lst.size(); i++){
            temp.next = new ListNode(lst.get(i));
            temp = temp.next;
        }
        return head;
    }
}
